import com.mongodb.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Dining {
    public static void main(String[] args) {

        Lock forks[]=new ReentrantLock[5];
        try{
            MongoClient mongoclient=new MongoClient("localhost");
            System.out.println("Connection to mongodb sucessful");
            DB db=mongoclient.getDB("mydb");
            System.out.println("database mydb created");
            DBCollection coll=db.createCollection("mycol",null);
            System.out.println("collection mycol created");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        for(int i=0;i<5;i++){
            forks[i]=new ReentrantLock();
        }
        Thread p1=new Thread(new Philosopher(forks[4],forks[0],"first"));
        Thread p2=new Thread(new Philosopher(forks[0],forks[1],"second"));
        Thread p3=new Thread(new Philosopher(forks[1],forks[2],"third"));
        Thread p4=new Thread(new Philosopher(forks[2],forks[3],"forth"));
        Thread p5=new Thread(new Philosopher(forks[3],forks[4],"fifth"));
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
    }
    
}
class Philosopher implements Runnable{
    Lock leftFork=new ReentrantLock();
    Lock rightFork=new ReentrantLock();
    String name;
    public Philosopher(Lock leftFork,Lock rightFork,String name){
        this.leftFork=leftFork;
        this.rightFork=rightFork;
        this.name=name;
    }

    public void run(){
        try{
            think(name);
            eat(leftFork,rightFork,name);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void eat(Lock leftfork,Lock rightFork, String name)throws Exception{
        leftFork.lock();
        rightFork.lock();
        try{
            MongoClient mongoclient=new MongoClient("localhost");
            DB db=mongoclient.getDB("mydb");
            DBCollection coll=db.getCollection("mycol");
            System.out.println(name +"eating.....");
            BasicDBObject doc1=new BasicDBObject(name , "eating...");
            coll.insert(doc1);
            Thread.sleep(1000);
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            System.out.println(name +"done eating and now thinking...");
            MongoClient mongoclient=new MongoClient("localhost");
            DB db=mongoclient.getDB("mydb");
            DBCollection coll=db.getCollection("mycol");
            BasicDBObject doc2=new BasicDBObject(name ,"done eating now thinking...");
            coll.insert(doc2);
            leftFork.unlock();
            rightFork.unlock();
            
        }
    }
    public void think(String name)throws Exception{
        try{
            MongoClient mongoclient=new MongoClient("localhost");
            DB db=mongoclient.getDB("mydb");
            DBCollection coll=db.getCollection("mycol");
            System.out.println(name +"thinking...");
            BasicDBObject doc=new BasicDBObject(name ,"thinking...");
            coll.insert(doc);
            Thread.sleep(1000);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}

/*
Output

Connection to mongodb sucessful
database mydb created
collection mycol created
secondthinking...
firstthinking...
fifththinking...
thirdthinking...
forththinking...
firsteating.....
fortheating.....
firstdone eating and now thinking...
forthdone eating and now thinking...
fiftheating.....
thirdeating.....
thirddone eating and now thinking...
fifthdone eating and now thinking...
secondeating.....
seconddone eating and now thinking...

*/



/* Terminal Output

admin1@admin1:~$ sudo su
[sudo] password for admin1: 
root@admin1:/home/admin1# mongo
MongoDB shell version: 2.4.14
connecting to: test
> db.mycol.find().pretty()
> db.mycol.find().pretty();
> show dbs;
dna	0.203125GB
local	0.078125GB
mydb	0.203125GB
test	(empty)
> use mydb;
switched to db mydb
> db.mycol.find().pretty();
{ "_id" : ObjectId("5ac1fde924ac7e532efd31c3"), "second" : "thinking..." }
{ "_id" : ObjectId("5ac1fde924ac7e532efd31c4"), "third" : "thinking..." }
{ "_id" : ObjectId("5ac1fde924ac7e532efd31c2"), "fifth" : "thinking..." }
{ "_id" : ObjectId("5ac1fde924ac7e532efd31c1"), "forth" : "thinking..." }
{ "_id" : ObjectId("5ac1fde924ac7e532efd31c0"), "first" : "thinking..." }
{ "_id" : ObjectId("5ac1fdeb24ac7e532efd31c5"), "fifth" : "eating..." }
{
	"_id" : ObjectId("5ac1fdec24ac7e532efd31c6"),
	"fifth" : "done eating now thinking..."
}
{ "_id" : ObjectId("5ac1fdec24ac7e532efd31c7"), "forth" : "eating..." }
{
	"_id" : ObjectId("5ac1fded24ac7e532efd31c8"),
	"forth" : "done eating now thinking..."
}
{ "_id" : ObjectId("5ac1fded24ac7e532efd31c9"), "third" : "eating..." }
{
	"_id" : ObjectId("5ac1fdee24ac7e532efd31ca"),
	"third" : "done eating now thinking..."
}
{ "_id" : ObjectId("5ac1fdee24ac7e532efd31cb"), "second" : "eating..." }
{
	"_id" : ObjectId("5ac1fdef24ac7e532efd31cc"),
	"second" : "done eating now thinking..."
}
{ "_id" : ObjectId("5ac1fdef24ac7e532efd31cd"), "first" : "eating..." }
{
	"_id" : ObjectId("5ac1fdf024ac7e532efd31ce"),
	"first" : "done eating now thinking..."
}
> 
*/

