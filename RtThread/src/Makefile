CC = gcc
CFLAGS = -Wall -fPIC -I/usr/jdk/include/ -I/usr/jdk/include/linux/
LDFLAGS = -lpthread

all : libJNITest.so

libJNITest.so : RtThread.o 
	$(CC) $(LDFLAGS) $^ -shared -o $@ 

%.o : %.c
	$(CC) -c $(CFLAGS) $< -o $@ 

clean :
	rm -rf libJNITest.so
	rm -rf *.o 
	rm -rf *~ 


