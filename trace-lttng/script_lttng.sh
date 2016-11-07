sudo lttng create error

sudo lttng enable-event -a -k

sudo lttng start

sudo ./test.sh $1

sudo lttng stop

