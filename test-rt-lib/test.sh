LD_LIBRARY_PATH=~/Rt_Thread/rt-thread-c/src/
export LD_LIBRARY_PATH

export CLASSPATH=$CLASSPATH:/usr/share/java/liblttng-ust-agent.jar


case $1 in
	1) java -classpath main-test-high.jar Main.Main ;;
	2) java -classpath main-test-diff.jar Main.Main ;;
	3) java -classpath main-test-error.jar Main.Main ;;
esac
