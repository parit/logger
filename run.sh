java -Dlog4j.debug -cp .:./bin/:./lib/jcommander.jar:./lib/jackson-core.jar:./lib/log4j-1.2.17.jar:./lib/slf4j-api-1.7.5.jar:./lib/slf4j-log4j12-1.7.5.jar com.jti.commandLine.Cmd -i /scratch/test/actmon50.txt -o /scratch/test/gaurav -fmt tab,sql,json -table logs