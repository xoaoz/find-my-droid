#!/usr/bin/env python
import os,sys,FetchMail,time,threading
try:
    import pygtk
    pygtk.require("2.0")
except:
    pass
try:
    import gtk
    import gtk.glade
except:
    print("This program need to be run in graphical environment!")
    sys.exit(1)

result="false"

class FMthread(threading.Thread):
    def __init__(self,user,passwd,maptyp,zoom):
        threading.Thread.__init__(self)
        self.user=user
        self.passwd=passwd
        self.maptyp=maptyp
        self.zoom=zoom
    def run(self):
        global result
        result=FetchMail.FetchMail(self.user,self.passwd,self.maptyp,self.zoom)
        

class HelloWorldGTK:
    """This is an Hello World GTK application"""
    def __init__(self):
        #Set the Glade file
        _localDir=os.path.dirname(__file__)
        curpath=os.path.normpath(os.path.join(os.getcwd(),_localDir))
        self.gladefile=os.path.join(curpath, "frame.glade")
        print(self.gladefile)
        self.wTree = gtk.glade.XML(self.gladefile)
        self.rat="satellite"
        #Get the Main Window, and connect the "destroy" event
        self.dia = self.wTree.get_widget("window2")
        self.pro = self.wTree.get_widget("window3")
        self.window = self.wTree.get_widget("window1")
        self.image=self.wTree.get_widget("image1")
        self.image.set_from_file(os.path.join(curpath,"path"))
        self.label=self.wTree.get_widget("label7")
        self.label.set_label("")
        self.window.show()
        if (self.window):
            self.window.connect("destroy", gtk.main_quit)
        dic = {"on_button1_clicked":self.rat4, "on_button2_clicked" : self.btnHelloWorld_clicked,"on_radiobutton1_clicked":self.rat1,"on_radiobutton2_clicked":self.rat2,"on_radiobutton3_clicked":self.rat3}
        self.wTree.signal_autoconnect(dic)
    def rat1(self,widget):
        self.rat="roadmap"
    
    def rat2(self,widget):
        self.rat="satellite"
    
    def rat3(self,widget):
        self.rat="hybrid"
    def rat4(self,widget):
        self.dia.hide()
    
        
        
    def btnHelloWorld_clicked(self, widget):
        user=self.wTree.get_widget("entry1").get_text()
        passwd=self.wTree.get_widget("entry2").get_text()
        zoom = self.wTree.get_widget("spinbutton1").get_value()
        #self.pro.show();
        print("email adress : "+user)
        print ("zoom : "+str(int(zoom)))
        print ("map type : "+self.rat+"\n")
        
        result=FetchMail.FetchMail(user,passwd,self.rat,int(zoom))
        #thread = FMthread(user,passwd,self.rat,int(zoom))
        #thread.start()
        #self.label.set_label("processing...")
        #thread.join()
        
        if(result=="false"):
            self.dia.show()
        else:
            self.image.set_from_file(result)
        
        
    
if __name__=="__main__":
    hwg=HelloWorldGTK()
    gtk.main()
