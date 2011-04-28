#!/usr/bin/env python
import sys,os,FetchMail

def copyfile(src,dest):
    fsrc=file(src,"rb")
    fdest=file(dest,"wb")
    fdest.write(fsrc.read())
    fsrc.close()
    fdest.close()
    
list=["aPhoneLocate_Windows,py","mail_address","mail_passwd","maproad","14","path.png"]

if(len(sys.argv)<=2 or  sys.argv[1]=="help"):
    print("Usage: \n aPhoneLocate_Windows.py  mail_address mail_passwd maptype(roadmap or satellite or hybrid default:\"maproad\") zoom( default:14) map_name(default: \"path.jpg\")\n example:\n aPhoneLocate_Windows.py someone@gmail.com  someonepasswd satellite 13 mypath.png")
    exit()

for i in range(1,len(sys.argv)):
    list[i]=sys.argv[i]

src = FetchMail.FetchMail(list[1],list[2],list[3],int(list[4]))
_localDir = os.path.dirname(__file__)
curpath = os.path.normpath(os.path.join(os.getcwd(),_localDir))
dest = os.path.join(curpath,list[5])
print("copying file...")
copyfile(src,dest)


