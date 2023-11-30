/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.config;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author patriaS
 */
@Configuration
//@PropertySource("file:///D:/jdbc.properties")
@PropertySource({"classpath:database.properties", "file:${jxconfig}"})
public class AppConfig {

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.driver}")
    private String driver;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        try {
//            String realPass = CryptoSecurity.decrypt(password);
            dataSource.setPassword(password);
        } catch (Exception ex) {
            Logger.getLogger(AppConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataSource;
    }

//    @Value("${jdbc.url.sd}")
//    private String urlSd;
//
//    @Value("${jdbc.username.sd}")
//    private String usernameSd;
//
//    @Value("${jdbc.password.sd}")
//    private String passwordSd;
//
//    @Value("${jdbc.driver.sd}")
//    private String driverSd;
//
//    @Bean
//    public DataSource dataSourceSd() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(driverSd);
//        dataSource.setUrl(urlSd);
//        dataSource.setUsername(usernameSd);
//        try {
////            String realPass = CryptoSecurity.decrypt(password);
//            dataSource.setPassword(passwordSd);
//        } catch (Exception ex) {
//            Logger.getLogger(AppConfig.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return dataSource;
//    }
//    @Value("${jdbc.url.hr}")
//    private String urlHr;
//
//    @Value("${jdbc.username.hr}")
//    private String usernameHr;
//
//    @Value("${jdbc.password.hr}")
//    private String passwordHr;
//
//    @Value("${jdbc.driver.hr}")
//    private String driverHr;
//
//    @Bean
//    public DataSource dataSourceHr() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(driverHr);
//        dataSource.setUrl(urlHr);
//        dataSource.setUsername(usernameHr);
//        try {
////            String realPass = CryptoSecurity.decrypt(password);
//            dataSource.setPassword(passwordHr);
//        } catch (Exception ex) {
//            Logger.getLogger(AppConfig.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println("driverHr : " + driverHr);
//        System.out.println("urlHr : " + urlHr);
//        System.out.println("usernameHr : " + usernameHr);
//        System.out.println("passwordHr : " + passwordHr);
//        return dataSource;
//    }
//
//    @Value("${jdbc.url.b2e}")
//    private String urlB2e;
//
//    @Value("${jdbc.username.b2e}")
//    private String usernameB2e;
//
//    @Value("${jdbc.password.b2e}")
//    private String passwordB2e;
//
//    @Value("${jdbc.driver.b2e}")
//    private String driverB2e;
//
//    @Bean
//    public DataSource dataSourceB2e() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(driverB2e);
//        dataSource.setUrl(urlB2e);
//        dataSource.setUsername(usernameB2e);
//        try {
////            String realPass = CryptoSecurity.decrypt(password);
//            dataSource.setPassword(passwordB2e);
//        } catch (Exception ex) {
//            Logger.getLogger(AppConfig.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return dataSource;
//    }
//    
//    @Value("${jdbc.url.wfs}")
//    private String urlWfs;
//
//    @Value("${jdbc.username.wfs}")
//    private String usernameWfs;
//
//    @Value("${jdbc.password.wfs}")
//    private String passwordWfs;
//
//    @Value("${jdbc.driver.wfs}")
//    private String driverWfs;
//
//    @Bean
//    public DataSource dataSourceWfs() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(driverWfs);
//        dataSource.setUrl(urlWfs);
//        dataSource.setUsername(usernameWfs);
//        try {
////            String realPass = CryptoSecurity.decrypt(password);
//            dataSource.setPassword(passwordWfs);
//        } catch (Exception ex) {
//            Logger.getLogger(AppConfig.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println("driverWfs : " + driverWfs);
//        System.out.println("urlWfs : " + urlWfs);
//        System.out.println("usernameWfs : " + usernameWfs);
//        System.out.println("passwordWfs : " + passwordWfs);
//
//        return dataSource;
//    }
    
}
