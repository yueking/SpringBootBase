package com.yueking.core.shiro;

import com.yueking.BaseTest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.SimpleByteSource;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShiroTest extends BaseTest {

    @Test
    public void testShiro() {
        System.out.println("========shiro");
        //1.获取SecurityManager factory
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shiro.ini");

        //2.使用securityManager 绑定 securityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3.获取 subject 及 创建 用户名 密码Token 身份/凭证
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("yueking", "123");

        subject.login(token);

        System.out.printf(":" + subject.isAuthenticated());
        Assert.assertEquals(true, subject.isAuthenticated());

        subject.logout();
    }

    @Test
    public void testShiroRealm() {
        System.out.println("========shiro");
        //1.获取SecurityManager factory
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shiro-realm.ini");

        //2.使用securityManager 绑定 securityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3.获取 subject 及 创建 用户名 密码Token 身份/凭证
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("yueking", "123");

        subject.login(token);

        System.out.printf(":" + subject.isAuthenticated());
        Assert.assertEquals(true, subject.isAuthenticated());

        subject.logout();

    }

    @Test
    public void testShiroJdbcRealm() {

        System.out.println("========shiro");
        //1.获取SecurityManager factory
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shiro-jdbc-realm.ini");

        //2.使用securityManager 绑定 securityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3.获取 subject 及 创建 用户名 密码Token 身份/凭证
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("admin", "666");
        boolean authenticated = subject.isAuthenticated();
        System.out.println("authenticated:" + authenticated);

        subject.login(token);
        authenticated = subject.isAuthenticated();
        System.out.println("authenticated:" + authenticated);
        System.out.println("hasRole has admin role:" + subject.hasRole("admin"));
        boolean system = subject.hasRole("system");
        System.out.println("hasRole system:" + system);
        System.out.println("isPermitted update:" + subject.isPermitted("update"));

    }

    @Test
    public void testShiroPermission() {
        System.out.println("========shiro");
        //1.获取SecurityManager factory
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shiro-permission.ini");

        //2.使用securityManager 绑定 securityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3.获取 subject 及 创建 用户名 密码Token 身份/凭证
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("yueking", "123");

        subject.login(token);

        System.out.println("---" + subject.isAuthenticated());
        Assert.assertTrue(subject.isPermitted("user:create"));
        Assert.assertTrue(subject.isPermittedAll("user:update", "user:delete"));

        subject.checkPermission("user:create");

    }

    @Test
    public void testPassword() {
        DefaultHashService hashService = new DefaultHashService();
        hashService.setHashAlgorithmName("SHA-512");
        hashService.setPrivateSalt(new SimpleByteSource("123"));
        hashService.setGeneratePublicSalt(true);
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());
        hashService.setHashIterations(1);

        HashRequest request = new HashRequest.Builder().setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("hello")).setSalt(ByteSource.Util.bytes("123")).setIterations(2).build();
        String hex = hashService.computeHash(request).toHex();
        System.out.println("hex:" + hex);

        DefaultPasswordService passwordService = new DefaultPasswordService();
        String yueking = passwordService.encryptPassword("yueking");
        System.out.println("encryptPassword yueking:" + yueking);

    }

    @Test
    public void testPassword2() {
        String algorithmName = "md5";
        String username = "yuekinger";
        String password = "123";

        String salt1 = username;
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        int hashIterations = 2;

        System.out.println("salt2:"+salt2);
        SimpleHash hash = new SimpleHash(algorithmName,username,salt1+salt2,hashIterations);
        String encodedPassword = hash.toHex();
        System.out.println("encodedPassword:"+encodedPassword);
    }

    @Test
    public void testTestPasswordRealm() {
        System.out.println("========shiro");
        //1.获取SecurityManager factory
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shiro-passwordservice.ini");

        //2.使用securityManager 绑定 securityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3.获取 subject 及 创建 用户名 密码Token 身份/凭证
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("yueking", "123");

        subject.login(token);

        System.out.println("---" + subject.isAuthenticated());
//        Assert.assertTrue(subject.isPermitted("user:create"));
//        Assert.assertTrue(subject.isPermittedAll("user:update","user:delete"));

//        subject.checkPermission("user:create");

    }

    @Test
    public void testHashedCredentialsMatcher2() {

        System.out.println("========shiro========");
        //1.获取SecurityManager factory
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shiro-passwordservice2.ini");

        //2.使用securityManager 绑定 securityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3.获取 subject 及 创建 用户名 密码Token 身份/凭证
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("aaa", "123");

        subject.login(token);

        System.out.println("---:" + subject.isAuthenticated());

    }


}