package com.matr.mysqlaws.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {


       /* Acceso CORS a todos los endpoints

        registry.addMapping("/**") // Permite CORS en todas las rutas
                .allowedOrigins("http://localhost:5005", "http://prb1-env.eba-3p9pi4mv.us-east-2.elasticbeanstalk.com/") // Orígenes permitidos
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                .allowedHeaders("*"); // Encabezados permitidos


        // Configuración para todos los endpoints que empiezan con "/api/public"
        registry.addMapping("/api/public/**")
                .allowedOrigins("http://localhost:5005", "http://prb1-env.eba-3p9pi4mv.us-east-2.elasticbeanstalk.com/")
                .allowedMethods("GET", "POST", "OPTIONS") // Solo GET, POST y OPTIONS permitidos
                .allowedHeaders("*");

        // Configuración específica para un endpoint en particular
        registry.addMapping("/api/private/**")
                .allowedOrigins("http://localhost:5005")
                .allowedMethods("GET") // Solo GET permitido
                .allowedHeaders("*");

        // Otra configuración para diferentes endpoints o métodos
        registry.addMapping("/api/another/**")
                .allowedOrigins("http://prb1-env.eba-3p9pi4mv.us-east-2.elasticbeanstalk.com/")
                .allowedMethods("POST", "PUT", "DELETE")
                .allowedHeaders("*");


         */
        registry.addMapping("/")
                .allowedOrigins("https://rom.tinttodry.com/","http://tinttodryrm-env.eba-dw4kxt3d.us-east-2.elasticbeanstalk.com/" ,"http://localhost:5005", "https://tinttodry.com/")
                .allowedMethods("GET", "POST", "OPTIONS") // Solo GET, POST y OPTIONS permitidos
                .allowedHeaders("*");

        registry.addMapping("/token")
                .allowedOrigins("https://rom.tinttodry.com/","http://tinttodryrm-env.eba-dw4kxt3d.us-east-2.elasticbeanstalk.com/" ,"http://localhost:5005", "https://tinttodry.com/")
                .allowedMethods("GET", "POST", "OPTIONS") // Solo GET, POST y OPTIONS permitidos
                .allowedHeaders("*");

        registry.addMapping("/con")
                .allowedOrigins("https://rom.tinttodry.com/","http://tinttodryrm-env.eba-dw4kxt3d.us-east-2.elasticbeanstalk.com/" ,"http://localhost:5005", "https://tinttodry.com/")
                .allowedMethods("GET", "POST", "OPTIONS") // Solo GET, POST y OPTIONS permitidos
                .allowedHeaders("*");





        registry.addMapping("/usuarios")
                .allowedOrigins("https://rom.tinttodry.com/","http://tinttodryrm-env.eba-dw4kxt3d.us-east-2.elasticbeanstalk.com/" ,"http://localhost:5005", "https://tinttodry.com/")
                .allowedMethods("GET" )
                .allowedHeaders("*")
                .allowCredentials(true);


        registry.addMapping("/elimusuario/{rfid}")
                .allowedOrigins("https://rom.tinttodry.com/","http://tinttodryrm-env.eba-dw4kxt3d.us-east-2.elasticbeanstalk.com/" ,"http://localhost:5005", "https://tinttodry.com/")
                .allowedMethods("DELETE", "OPTIONS") // Solo GET, POST y OPTIONS permitidos
                .allowedHeaders("*");




        registry.addMapping("/usuarioedit/{rfid}")
                .allowedOrigins("https://rom.tinttodry.com/","http://tinttodryrm-env.eba-dw4kxt3d.us-east-2.elasticbeanstalk.com/" ,"http://localhost:5005", "https://tinttodry.com/")
                .allowedMethods("GET", "OPTIONS") // Solo GET, POST y OPTIONS permitidos
                .allowedHeaders("*");


        registry.addMapping("/actualizar/{rfid}")
                .allowedOrigins("https://rom.tinttodry.com/","http://tinttodryrm-env.eba-dw4kxt3d.us-east-2.elasticbeanstalk.com/" ,"http://localhost:5005", "https://tinttodry.com/")
                .allowedMethods("PATCH", "OPTIONS") // Solo GET, POST y OPTIONS permitidos
                .allowedHeaders("*");



        registry.addMapping("/registrarusuario")
                .allowedOrigins("https://rom.tinttodry.com/","http://tinttodryrm-env.eba-dw4kxt3d.us-east-2.elasticbeanstalk.com/" ,"http://localhost:5005", "https://tinttodry.com/")
                .allowedMethods("POST", "OPTIONS") // Solo GET, POST y OPTIONS permitidos
                .allowedHeaders("*");




        registry.addMapping("/login")
                .allowedOrigins("https://rom.tinttodry.com/","http://tinttodryrm-env.eba-dw4kxt3d.us-east-2.elasticbeanstalk.com/" ,"http://localhost:5005", "https://tinttodry.com/")
                .allowedMethods("POST", "OPTIONS") // Solo GET, POST y OPTIONS permitidos
                .allowedHeaders("*");

        registry.addMapping("/check_uid/{rfid}")
                .allowedOrigins("https://rom.tinttodry.com/","http://tinttodryrm-env.eba-dw4kxt3d.us-east-2.elasticbeanstalk.com/" ,"http://localhost:5005", "https://tinttodry.com/")
                .allowedMethods("GET", "OPTIONS") // Solo GET, POST y OPTIONS permitidos
                .allowedHeaders("*");

        registry.addMapping("/estado/{id}")
                .allowedOrigins("https://rom.tinttodry.com/","http://tinttodryrm-env.eba-dw4kxt3d.us-east-2.elasticbeanstalk.com/" ,"http://localhost:5005", "https://tinttodry.com/")
                .allowedMethods("GET", "OPTIONS") // Solo GET, POST y OPTIONS permitidos
                .allowedHeaders("*");

        registry.addMapping("/actualizarlav/{id}")
                .allowedOrigins("https://rom.tinttodry.com/","http://tinttodryrm-env.eba-dw4kxt3d.us-east-2.elasticbeanstalk.com/" ,"http://localhost:5005", "https://tinttodry.com/")
                .allowedMethods("PUT", "OPTIONS") // Solo GET, POST y OPTIONS permitidos
                .allowedHeaders("*");


        registry.addMapping("/check_saldo/{hexs}")
                .allowedOrigins("https://rom.tinttodry.com/","http://tinttodryrm-env.eba-dw4kxt3d.us-east-2.elasticbeanstalk.com/" ,"http://localhost:5005", "http://localhost:5000", "https://tinttodry.com/","http://192.168.1.65:5000/","http://192.168.1.65:5005/","http://192.168.100.239:5000/","http://192.168.100.239:5005/")
                .allowedMethods("GET", "OPTIONS") // Solo GET, POST y OPTIONS permitidos
                .allowedHeaders("*");
    }
}
