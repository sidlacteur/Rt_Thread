
LD_LIBRARY_PATH='../rt-thread-c/src'
export LD_LIBRARY_PATH

#export CLASSPATH=$CLASSPATH:/usr/share/java/liblttng-ust-agent.jar

export ajrt=aspectj8/lib/aspectjrt.jar
export ajwd=aspectj8/lib/aspectjweaver.jar
export CLASSPATH="$ajwd:$ajrt"

java -Xincgc  -javaagent:$ajwd -classpath $ajwd:$ajrt:main_incr.jar Main.Main
