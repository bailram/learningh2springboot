# learning h2 springboot
### Basic configuration
### pom.xml
```xml
    ...

    <dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

    ...
```

#### application.properties
```properties
# enable h2 console on web
spring.h2.console.enabled=true
# shorter access console path /h2-console to /h2
spring.h2.console.path=/h2

spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
```
#### java main class
```java
    ...

    public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(H2Application.class, args);
		// init person repository
		PersonRepository personRepository = context.getBean(PersonRepository.class);
		// init data
		Person person1 = new Person("Dhimas Bayu", "Malang");
		personRepository.save(person1);
		// should success
		Optional<Person> resultById = personRepository.findById(1L);
		if (resultById.isPresent()) {
			System.out.println(resultById.toString());
		} else {
			System.out.println("Data tidak ditemukan");
		}
		// should fail
		Optional<Person> resultById1 = personRepository.findById(2L);
		if (resultById1.isPresent()) {
			System.out.println(resultById1.toString());
		} else {
			System.out.println("Data tidak ditemukan");
		}
		// should success
		Person result2 = personRepository.findByName("Dhimas Bayu");
		if (result2 != null) {
			System.out.println(result2.toString());
		} else {
			System.out.println("Data tidak ditemukan");
		}
		// should fail
		Person result = personRepository.findByName("Dhimas");
		if (result != null) {
			System.out.println(result.toString());
		} else {
			System.out.println("Data tidak ditemukan");
		}
	}
	
    ...
```
