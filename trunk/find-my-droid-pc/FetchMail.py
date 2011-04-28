#!/usr/bin/env python 
import imaplib,getpass,time,os,sys,urllib2

def FetchMail(user,passwd,maptyp="roadmap",zoom=14):
    if(user.endswith("gmail.com")):
        M=imaplib.IMAP4_SSL("imap.gmail.com")
    elif(user.endswith("163.com")):
        M=imaplib.IMAP4_SSL("imap.163.com",993)
    else:
        print("email not supported!")

        
    try:
        M.login(user,passwd)
    except:
        print("login failed")
        return "false"

    count=M.select()
    if(count==0):
        return "false"
    typ,data=M.search(None,'Subject','"location changed"')
    idset= data[0].split()
    num=len(data[0].split())
    #print data[0]
    print("search mails and the number is :"+str(num))
    location="l,l"
    #url0="http://maps.google.com/maps/api/staticmap?center="
    #url1="&zoom=16&size=512x512&maptype=satellite"
    marks="&markers=size:mid|color:red|label:S|"
    path="&path=color:0x0000ff|weight:2"
    urlend="&sensor=false"
    mrks=""
    
    if(num < 10):
        rg=-1
    else:
        rg=num-11
                    
    for i in range(num-1,rg,-1):
        print("now processing mail NO:"+str(idset[i]))
        typ,data=M.fetch(idset[i],'RFC822')
        fulltext=data[0][1].split("\r\n")
        
        #print len(fulltext)
        for i in range(0,len(fulltext),1):

            text=fulltext[i]
            #print text
            if(text.startswith("longitude,latitude: ")):
                strr=text[20:]
                loc=strr.split(",")
                location=loc[1]+","+loc[0]
                print("latitude,longitude: "+location)
                path+="|"
                path+=location
                #url=url0
                #url+=location
                #url+=url1+marks
                #url+=location
                #url+=urlend
                mrks+=marks+location
                #print url
                #pic=urllib2.urlopen(url).read()
                #fpath="/home/niuniu/program/"
                #fpath+=str(i)
                #f=file(fpath,"wb")
                #f.write(pic)
                #f.close()
                #time.sleep(2)
                break;
    url="http://maps.google.com/maps/api/staticmap?&zoom="+str(zoom)+"&size=640x640&maptype="+maptyp+path+mrks+urlend
    print("\ngoogle map api url generated===============>")
    print(url)
    print("")
    print("now invoking the google map api to get map\n")
    pic=urllib2.urlopen(url).read()
    _localDir=os.path.dirname(__file__)
    curpath=os.path.normpath(os.path.join(os.getcwd(),_localDir))
    fpath=os.path.normpath(os.path.join(os.getcwd(),_localDir))
    fpath=os.path.join(fpath,"path"+str(idset[num-1])+maptyp+str(zoom)+".png")
    print("get map and map path is :"+fpath)
    f=file(fpath,"wb")
    f.write(pic)
    f.close()
    M.close()
    M.logout()
    print("done.")
    return fpath


