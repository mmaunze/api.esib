package com.esib.esib;

import java.util.logging.Logger;
import static org.springframework.boot.SpringApplication.run;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Meldo Maunze
 */
@SpringBootApplication
public class EsibApplication {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
		run(EsibApplication.class, args);
	}
    private static final Logger LOG = Logger.getLogger(EsibApplication.class.getName());

}
