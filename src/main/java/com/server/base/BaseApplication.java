package com.server.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@Slf4j
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
        log.error("-----------------------------------------------------------------------------");
        log.error(" ⊂_ヽ                      ______                 _              _   ");
        log.error("　 ＼＼  Λ＿Λ                | ___ \\               (_)            | |  ");
        log.error("　 　＼( ‘ㅅ’ ) 두둠칫         | |_/ / _ __   ___     _   ___   ___ | |_ ");
        log.error("　　　 >　⌒ヽ                 |  __/ | '__| / _ \\   | | / _ \\ / __|| __| ");
        log.error("　　　/ 　 へ＼                | |    | |   | (_) |  | ||  __/| (__ | |_  ");
        log.error("　　 /　　/　＼＼               \\_|    |_|    \\___/   | | \\___| \\___| \\__| ");
        log.error("　　 ﾚ　ノ　　 ヽ_つ                                 _/ |                 ");
        log.error("　　/　/두둠칫                                     |__/                   ");
        log.error("　 /　/|                        ______  ");
        log.error(" (　(ヽ                        | ___ \\ ");
        log.error("　|　|、＼                       | |_/ /  __ _  ___   ___  ");
        log.error("　| 丿 ＼ ⌒)                    | ___ \\ / _` |/ __| / _ \\");
        log.error("　| |　　) /                    | |_/ /| (_| |\\__ \\|  __/");
        log.error("`ノ )　　Lﾉ                     \\____/  \\__,_||___/ \\___|");
        log.error("/_ﾉ");
        log.error("-----------------------------------------------------------------------------");
    }
}
