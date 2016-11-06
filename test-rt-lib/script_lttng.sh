sudo lttng create --output /home/emeraude/lttng-trace/cmd/error error

sudo lttng enable-event -a -k

sudo lttng start

sudo ./script_run_error.sh

sudo lttng stop

