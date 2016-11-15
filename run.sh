
LD_LIBRARY_PATH='/home/emeraude/Rt_Thread/rt-thread-c/src'
export LD_LIBRARY_PATH

#export CLASSPATH=$CLASSPATH:/usr/share/java/liblttng-ust-agent.jar


export ajrt=aspectj8/lib/aspectjrt.jar
export ajwd=aspectj8/lib/aspectjweaver.jar
export CLASSPATH="$ajwd:$ajrt"
#ajc -inpath lib-with-1.jar -aspectpath lib-with-1.jar -outjar lib-ajc.jar

#aj -javaagent:aspectjweaver-1.7.2.jar -classpath "main-with.jar;lib-ajc.jar" Main.Main

java -javaagent:$ajwd -classpath $ajwd:$ajrt:main.jar Main.Main
