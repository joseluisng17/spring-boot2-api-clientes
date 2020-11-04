package com.nombreempresa.springboot.backend.apirest.auth;

public class JwtConfig {
	
	public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";
	
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEpAIBAAKCAQEAlzsscBiYmpNYE0zi4RETMZqJ1Iqotyuo4KxgiFfRe3yWzX7H\r\n" + 
			"OUp9BygWQ9JN9O4SZtVXG5YLKGwmatlJlwDPWN3h66XmG/o+6FCPXV6rIydQAlMV\r\n" + 
			"XYjXDpBdr5trgx/iYxadCVIPweSipu9YqnoILB49Rx8+j2KsI9MsnA18ZBqps0Mk\r\n" + 
			"tmE8tprrWfc0C9U2t2jYKl5zjdb3SkMw1wbCLSI5SjPbB79iUCh/4B9uJE4MLSIX\r\n" + 
			"+wlUTKyMweK9WxCcJn4ZpeCgd8iznncuMGs0n7wgnVwl1mEZXppQNPUAGVvAOq2j\r\n" + 
			"Bm5sw/qYRCtKG4/7mlUlsPouq1Lm8AU7O3uNYQIDAQABAoIBAQCUHRZGqzA6o/hs\r\n" + 
			"3q6N86UyvcIj8bGPxCjf8rOQlUHLiZhAptwD1lphefdkcUJ8kfZf1K49jFRdQJ7X\r\n" + 
			"ovwZ8mchCB7OJmsaF5so+E2RCrjd8xC/VhPkybKmxumapcNEZyPYHDus9zwLkBtl\r\n" + 
			"F6FlRaNfYjZtw8IHuIITjC1yoRqlBp6hHfy8f9aakviwQrT/lrfIKm2MDoP5BRp1\r\n" + 
			"RLgqcLGAemWz0GBOu2h+DpB79KIHX/Vfgq9IRl4zTtdbo3kIBkl31xkECZs/evVU\r\n" + 
			"gopHBZ5TkUjg4P1e1kJLUohf4gZi4OhlPwmKN/3tR2k4M6B0gCs/0Qw2jIyz56cd\r\n" + 
			"BWgJ3S6JAoGBAMbqNKUACHt5aUVzpah0dcAJc7vYXqVRJrDctxXDhrVVAfnZvS/I\r\n" + 
			"zD0MVup7dXipf79JFruZyvEM9ZIeQfFIEvY4BLVsTvskUA6SgXWC3L7/qy8/Va9g\r\n" + 
			"Wl5yUjJaaw90AhdaG5plOXGzeu7PqT7GKJOkaVcyD/he1dWleWMsN/vTAoGBAMKh\r\n" + 
			"wqfn0Q6KbVuCzVs+HQsLeAb9BZ7fYSOhrdeMO99fKG5p+Fj5/9Ll/8Zwysytolpj\r\n" + 
			"ws78qE1OUvqhGJ1AlL8/3AFnTjZvnNccgRjhT3wveZbEFMe2rgwg7vHqa0i23sF6\r\n" + 
			"JHP6CeW4rTMYvUL0mEPdlzRSSh7QLzOnE0UpVdV7AoGBAKde2f0cEgcTGaj+rh0Y\r\n" + 
			"IRv82Azu+nRP3CZlEQiXBPn/FTKOK8nRQ0j64Qdb6nQk6wChFyezODerl9Xi78l9\r\n" + 
			"w7a5W/zrrWlUHKimTZf7DJMJvZh7yjVO9bsSpmcH/Z17F2dewmsfUT6CrqXSxa20\r\n" + 
			"PuA8LwbXcOijo3mpSoJP6qtRAoGAfo7e232C1BixKXsxe241RnRLtQecOwV3zcqZ\r\n" + 
			"FvsKB6KB0OI5aeoOLp3+JpQ7WsJhOrN5/kwOSE5w6KYOMlrHHmEw9vysv/rtmnbf\r\n" + 
			"f0UrAU6Bmsaf2IlyPVLA7S+IWX0H9fktcPiarPTcMsiYcMxJon+ZjZ0gs06eEtFG\r\n" + 
			"V4ecV7cCgYBoLyupzdrzw8B68uqTY8chU5gJ4SckxHrxxmEVuLbeekFHWufVZcdQ\r\n" + 
			"fhjBlbHT03DZ4rZlge9CZpwnzvPwP/pkwnCvlEd9v+3Zi+9w3QWOSEOpQN19d28J\r\n" + 
			"fm8sK1yuoOckuKgtca7J2A8JxrtYU7VZ7OucC8S+OchBBGgtAR0avg==\r\n" + 
			"-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlzsscBiYmpNYE0zi4RET\r\n" + 
			"MZqJ1Iqotyuo4KxgiFfRe3yWzX7HOUp9BygWQ9JN9O4SZtVXG5YLKGwmatlJlwDP\r\n" + 
			"WN3h66XmG/o+6FCPXV6rIydQAlMVXYjXDpBdr5trgx/iYxadCVIPweSipu9YqnoI\r\n" + 
			"LB49Rx8+j2KsI9MsnA18ZBqps0MktmE8tprrWfc0C9U2t2jYKl5zjdb3SkMw1wbC\r\n" + 
			"LSI5SjPbB79iUCh/4B9uJE4MLSIX+wlUTKyMweK9WxCcJn4ZpeCgd8iznncuMGs0\r\n" + 
			"n7wgnVwl1mEZXppQNPUAGVvAOq2jBm5sw/qYRCtKG4/7mlUlsPouq1Lm8AU7O3uN\r\n" + 
			"YQIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";

}
