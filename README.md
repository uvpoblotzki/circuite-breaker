# Service absichern über Hystrix

Bei der Verschachtellung von Services kommt es regelmäßig zu fehlern. Wenn die Services nicht von einander isoliert werden, kann ein Fehler in einem Service die Komplette Anwendung beeinträchtigen. Mit Hystrix können Dienste von einander getrennt werden und in unterschiedlichen ThreadPools ausgeführt werden. In diesem Beispiel wird von einem String MVC Controller ein Service ausgerufen. Dieser ist synchronisiert und blockiert alle anfragenden Threads für eine fixe Dauer. So können sich Anfragen leicht auf schaukeln. 

Das Beispiel wurde mit Spring-Boot erstellt und kann über zwei Profile konfiguriert werden: "Standard" und "Hystrix". Im ersten Fall wird der Service direkt aufgerufen. Im zweiten Fall werden die Aufrufe über Hystrix abgesichert. 

     mvn spring-boot:run -Dspring.profiles.active="Standard"

oder
 
     mvn spring-boot:run -Dspring.profiles.active="Hystrix" 

Die Dauer einer Serviceanfrage kann über den Parameter "service.blocking-duration" (Standard 30 ms) in Millisekunden über JMX eingestellt werden. Hystrix wurde so konfiguriert, dass maximal 20 gleichzeitige Anfragen laufen können und eine einzelne nicht länger als 2000 ms dauern darf. 

Es wurde ein JMeter Testfall hinzugefügt, um einwenig Last auf die Anwendung zugeben.  
