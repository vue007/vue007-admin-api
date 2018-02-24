package com.vue007.admin.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * @author Akai
 * @date 2017-12-23 14:06:43
 */
public class OssStsUtil {

    static Logger log = LoggerFactory.getLogger(OssStsUtil.class);

    private static final String REGION_CN_HANGZHOU = "cn-hangzhou";

    private static final String STS_API_VERSION = "2015-04-01";

    private static final String FILE_NAME = "oss.json";

    public static String OSS_CONTENT = "";

    public static void setOssContent(Resource resource) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        StringBuffer message=new StringBuffer();
        String line = null;
        while((line = br.readLine()) != null) {
            message.append(line);
        }
        String defaultString=message.toString();
        OssStsUtil.OSS_CONTENT=defaultString.replace("\r\n", "").replaceAll(" +", "");

    }

//    static {
//        init();
//    }
//
//    synchronized private static void init(){
//        if(Strings.isNullOrEmpty(OSS_CONTENT)){
//            String path = OssStsUtil.class.getClassLoader().getResource(FILE_NAME).getPath();
//            System.out.println(path);
//            OSS_CONTENT = FileUtils.readFile(path);
//        }
//    }

    private static AssumeRoleResponse assumeRole(String accessKeyId, String accessKeySecret, String roleArn, String roleSessionName, String policy, Long durationSeconds) throws ClientException {
        try {
            // 创建一个 Aliyun Acs Client, 用于发起 OpenAPI 请求
            IClientProfile profile = DefaultProfile.getProfile(REGION_CN_HANGZHOU, accessKeyId, accessKeySecret);
            DefaultAcsClient client = new DefaultAcsClient(profile);
            // 创建一个 AssumeRoleRequest 并设置请求参数
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setVersion(STS_API_VERSION);
            request.setMethod(MethodType.POST);
            request.setProtocol(ProtocolType.HTTPS);

            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setPolicy(policy);
            if (durationSeconds != null) {
                request.setDurationSeconds(durationSeconds);
            }
            // 发起请求，并得到response
            AssumeRoleResponse acsResponse = client.getAcsResponse(request);

            return acsResponse;
        }catch (ClientException e){
            throw e;
        }
    }

    public static Map<String,Object> getStsInfo(){
        Map<String,Object> map = Maps.newHashMap();
        if(map.isEmpty()){
            String data = OSS_CONTENT;
            if(Strings.isNullOrEmpty(data)){
                return null;
            }
            JSONObject jsonObj = JSON.parseObject(data);
            String accessKeyId = jsonObj.getString("AccessKeyID");
            String accessKeySecret = jsonObj.getString("AccessKeySecret");

            // RoleArn 需要在 RAM 控制台上获取
            String roleArn = jsonObj.getString("RoleArn");
            long durationSeconds = jsonObj.getLong("TokenExpireTime");
            String roleSessionName = jsonObj.getString("RoleSessionName");
            String policy = jsonObj.getJSONObject("Policy").toString();
            String endpoint = jsonObj.getString("Endpoint");
            String bucket = jsonObj.getString("Bucket");
            try {
                AssumeRoleResponse assumeRoleResponse = assumeRole(accessKeyId, accessKeySecret, roleArn, roleSessionName, policy, durationSeconds);
                AssumeRoleResponse.Credentials credentials = assumeRoleResponse.getCredentials();
                map.put("accessKeyId", credentials.getAccessKeyId());
                map.put("accessKeySecret",credentials.getAccessKeySecret());
                map.put("securityToken",credentials.getSecurityToken());
                map.put("expiration", credentials.getExpiration());
                map.put("endpoint", endpoint);
                map.put("bucket", bucket);
            }catch (ClientException e){
                log.error("OssStsUtil getStsInfo is error :"+e.getMessage(),e);
            }
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println(JSONObject.toJSONString(getStsInfo()));
    }
}
