package snippet;

public class Snippet {
	public static void main(String[] args) {
		<build>
				<plugins>
					<plugin>
						<artifactId>maven-compiler-plugin</artifactId>
						<configuration>
							<source>1.8</source>
							<target>1.8</target>
						</configuration>
					</plugin>
					<plugin>
						<artifactId>maven-war-plugin</artifactId>
						<version>2.3</version>
						<configuration>
							<failOnMissingWebXml>false</failOnMissingWebXml>
						</configuration>
					</plugin>
				</plugins>
			</build>
	}
}

