#spring.datasource.url=jdbc:h2:mem:~/testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
#spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.url=jdbc:h2:~/testdb;AUTO_SERVER=TRUE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=sa
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto = update
spring.datasource.initialization-mode=always
logging.level.com.np.issue.tracker=trace
