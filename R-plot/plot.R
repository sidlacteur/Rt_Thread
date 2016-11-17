args = commandArgs(trailingOnly=TRUE)

yas = read.csv(args[1])

x <- seq(0, 70, length=1000)
hx <- dweibull(x,2,6)




names(yas)<- c("Time")


sd<-sapply(yas$Time,function(x) {
  mA<-max(yas$Time)
  mI<-min(yas$Time)
  range <- mA-mI
  intervals<-100000
  elementary<-range/intervals
  k<-round(x/elementary)
  debut<-k*elementary
  return (round(debut,2))
}
)

res<-aggregate(sd,by=list(sd),length)
names(res)<-c("Class","Appearances")

path<-paste(args[1],".pdf",sep="")

pdf(path,w=7,h=5)
plot(x, hx, type="l", lty=2, xlab="Temps",
     ylab="Probabilité d'apparition",xaxt='n')

 l1<-max(res$Appearances)
 l2<-min(res$Appearances)

res$Appearances<-(0.13*res$Appearances)/((l1-l2)+1)
par(new=TRUE)

barplot(res$Appearances, names.arg=res$Class, xlab="",ylab="",yaxt='n',ylim=c(0,0.14))

dev.off()
