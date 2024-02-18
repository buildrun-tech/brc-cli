package tech.buildrun.cli;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.jline.PromptProvider;

@SpringBootApplication
public class BrcApplication implements PromptProvider {

	public static void main(String[] args) {
		SpringApplication.run(BrcApplication.class, args);
	}

	@Override
	public AttributedString getPrompt() {
		return new AttributedString("brc:>",
				AttributedStyle.DEFAULT.foreground(AttributedStyle.CYAN)
		);
	}

}
