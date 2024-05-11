import os

from flask import *
from werkzeug.utils import secure_filename
from DBConnection import *

android_bp = Blueprint('android', __name__)

@android_bp.route('/user-login',methods=['post'])
def logincode():
    print (request.form)
    uname=request.form['uname']
    pswd=request.form['pass']
    qry="select * from user where email=%s and password=%s and type='user'"
    val=(uname,pswd)
    res=selectone(qry,val)
    if res is None:
        return jsonify({'task':'invalid'})

    else:
        return jsonify({'task': 'valid','id':res[0] ,'name':res[1],'email':res[9]} )




@android_bp.route('/user_registration',methods=['post'])
def user_registration():
    f_name = request.form['fname']
    l_name = request.form['lname']
    h_name = request.form['hname']
    place = request.form['place']
    post = request.form['post']
    pin = request.form['pin']
    phone = request.form['phone']
    email = request.form['email']
    district = request.form['dis']
    gender = request.form['radio']
    password = request.form['pswd']

    q = "insert into user values(null,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,'user')"
    val = (f_name, l_name, h_name, place, post, pin, district, gender, email, phone, password)
    iud(q,val)
    return jsonify({'task': 'success'})






@android_bp.route('/view_nrby_station',methods=['post'])
def view_nrby_station():
    print(request.form)
    lati=request.form['lati']
    longi=request.form['longi']
    q="SELECT `station`.*,(3959 * ACOS ( COS ( RADIANS(%s) ) * COS( RADIANS( `station`.`latitude`) ) *COS( RADIANS(`station`.`longitude` ) - RADIANS(%s) ) + SIN ( RADIANS(%s) ) * SIN( RADIANS( `station`.`latitude` ) ))) AS user_distance FROM station  HAVING user_distance  < 1000"
    v=(lati,longi,lati)
    res=androidselectall(q,v)
    print(res)
    return jsonify(res)


@android_bp.route('/view_nrby_resto',methods=['post'])
def view_nrby_resto():
    print(request.form)
    lati = request.form['lati']
    longi = request.form['longi']
    q="SELECT `restaurant`.*,(3959 * ACOS ( COS ( RADIANS(%s) ) * COS( RADIANS( `restaurant`.`latitude`) ) *COS( RADIANS(`restaurant`.`longitude` ) - RADIANS(%s) ) + SIN ( RADIANS(%s) ) * SIN( RADIANS( `restaurant`.`latitude` ) ))) AS user_distance FROM restaurant  HAVING user_distance  < 1000"
    v = (lati,longi,lati)
    res = androidselectall(q, v)
    print(res)
    return jsonify(res)


@android_bp.route('/view_fecilites',methods=['post'])
def view_fecilites():
    sid=request.form['cid']
    print(sid)
    q="SELECT `fecilites`.*,`station`.`name` FROM `station` JOIN `fecilites` ON `fecilites`.`s_id`=`station`.`s_id` where `fecilites`.s_id=%s"
    res=androidselectall(q,sid)
    print(res)
    return jsonify(res)


@android_bp.route('/view_rating',methods=['post'])
def view_rating():
    q="SELECT `feedback`.`feedback`,AVG(`rating`)AS r,`station`.name,`station`.s_id,`station`.place,`station`.phone,`station`.email,`station`.image FROM `station` JOIN `feedback` ON `feedback`.`s_id`=`station`.s_id GROUP BY `feedback`.`s_id` ORDER BY r DESC"
    res=androidselectallnew(q)
    print(res)
    return jsonify(res)


@android_bp.route('/view_station',methods=['post'])
def view_station():



    q="SELECT * from station WHERE `status`='cc'"
    res=androidselectallnew(q)
    print(res)
    return jsonify(res)



@android_bp.route('/view_cc',methods=['post'])
def view_cc():
    place=request.form['place']
    print(place,"jhhhhhhhhhhhh")

    q="SELECT * from station where place=%s"
    res=androidselectall(q,place)
    print(res)
    return jsonify(res)


@android_bp.route('/view_rate_info',methods=['post'])
def view_rate_info():
    q=""
    res=androidselectall(q)
    return jsonify(res)


@android_bp.route('/book_slot',methods=['post'])
def book_slot():
    s_id = request.form['s_id']
    # u_id = request.form['u_id']
    bdate = request.form['bdate']
    btime = request.form['btime']
    print(request.form)
    qry="SELECT * FROM `booking` WHERE `date`=%s AND `time_slot`=%s AND `s_id`=%s "
    val=(bdate,btime,s_id)
    res=selectone(qry,val)
    if res is None:
        print("aaaaaaaaaaaaaaaaaaaaaa")
        return jsonify({'task': 'success'})
    else:
        print("bbbbbbbbbbbbbbbbbbbbb")
        return jsonify({'task': 'fail'})


    # if res is None:
    #     q="insert into booking values(null,%s,%s,%s,%s,'accepted')"
    #     val=(s_id,u_id,bdate,btime)
    #     iud(q,val)
    #     return jsonify({'task': 'success'})
    # else:
    #     return jsonify({'task': 'fail'})




# @android_bp.route('/book_slot',methods=['post'])
# def book_slot():
#     s_id = request.form['s_id']
#     u_id = request.form['u_id']
#     bdate = request.form['bdate']
#     btime = request.form['btime']
#
#     ac_no = request.form['ac_no']
#     ac_name = request.form['ac_name']
#     ifsc = request.form['ifsc']
#     key = request.form['key']
#     amount = request.form['amount']
#
#     print(request.form)
#     qry="SELECT * FROM `booking` WHERE `date`=%s AND `time_slot`=%s AND `s_id`=%s "
#     val=(bdate,btime,s_id)
#     res=selectone(qry,val)
#     if res is None:
#         q="insert into booking values(null,%s,%s,%s,%s,'accepted')"
#         val=(s_id,u_id,bdate,btime)
#         iud(q,val)
#
#
#
#
#         return jsonify({'task': 'success'})
#     else:
#         return jsonify({'task': 'fail'})
#




@android_bp.route('/view_book_sts',methods=['post'])
def view_book_sts():
    u_id = request.form['u_id']
    q= " SELECT `booking`.*,`station`.* ,booking.status bstat FROM `booking` JOIN `station` ON `booking`.`s_id`=`station`.`s_id` WHERE `booking`.`u_id`=%s order by date DESC "
    res=androidselectall(q,u_id)
    print(res)
    return jsonify(res)


@android_bp.route('/add_complaint',methods=['post'])
def add_complaint():
    sid=request.form['sid']
    uid=request.form['lid']
    complaint = request.form['complaint']
    q="INSERT INTO `complaint` VALUES(NULL,%s,%s,%s,'pending',CURDATE(),CURTIME())"
    val =(uid,sid,complaint)
    iud(q, val)
    return jsonify({'task': 'valid'})

@android_bp.route('/view_reply',methods=['post'])
def view_reply():
    lid=request.form['lid']
    q="SELECT `complaint`.*,`station`.`name` FROM `complaint` JOIN `station` ON `complaint`.`s_id`=`station`.`s_id` WHERE `complaint`.`u_id`=%s"
    res=androidselectall(q,lid)
    return jsonify(res)

@android_bp.route('/viewplace',methods=['post'])
def viewplace():

    q="SELECT  DISTINCT `place` FROM `station`"
    res=androidselectallnew(q)
    return jsonify(res)




@android_bp.route('/sent_feedback',methods=['post'])
def sent_feedback():
    feedback = request.form['feedback']
    rating = request.form['rating']
    s_id = request.form['s_id']
    u_id = request.form['lid']
    q="INSERT INTO `feedback` VALUES(NULL,%s,%s,%s,%s,CURDATE(),CURTIME())"
    val = (u_id,s_id,feedback,rating)
    iud(q, val)
    return jsonify({'task': 'valid'})

@android_bp.route('/payment',methods=['post'])
def payment():
    amt=request.form['amt']
    lid=request.form['u_id']
    s_id=request.form['slot']
    date=request.form['date']
    time=request.form['time']
    # ac_no = request.form['ac_no']
    # ac_name = request.form['ac_name']
    # ifsc = request.form['ifsc']
    # key = request.form['key']
    # amount = request.form['amount']
    #
    # s_id = request.form['kid']
    # u_id = request.form['u_id']
    # bdate = request.form['date']
    # btime = request.form['time']




    #
    # q1="INSERT INTO `payment` VALUES(NULL,%s,%s,%s,%s,%s)"
    q2 = "insert into booking values(null,%s,%s,%s,%s,'paid')"
    # val=(ac_no,ac_name,ifsc,key,amount)
    val2=(s_id,lid,date,time)
    bkid=iud(q2,val2)
    qry="insert into payment_details values(null,%s,%s,now())"
    val=(bkid,amt)
    iud(qry,val)
    qry="UPDATE `payment` SET `balance`=`balance`+%s WHERE `s_id`=%s"
    val=(amt,s_id)
    iud(qry,val)
    return jsonify({'task': 'success'})


@android_bp.route('/cancel_booking',methods=['post'])
def cancel_booking():
    b_id=request.form['bid']
    q= "update booking set status='cancelled' where b_id=%s"
    iud(q,b_id)
    qry = "SELECT s_id FROM `booking` WHERE `b_id`=%s"
    res = selectone(qry, b_id)
    if res is not None:
        qry = "UPDATE `payment` SET `balance`=`balance`-145 WHERE `s_id`=%s"
        val = (res[0])
        iud(qry, val)

    return jsonify({'task':'valid'})




@android_bp.route("/alertnoti",methods=['post'])
def alertnoti():
    lid=request.form['uid']
    qry="SELECT * FROM `booking` WHERE `u_id`=%s AND `status`='reject'"
    res=androidselectall(qry,str(lid))
    print(res)
    for i in res:
        qry="update `booking`  set `status`='rejected'  WHERE `b_id`=%s"
        iud(qry,i['b_id'])
    return jsonify(res)

@android_bp.route("/currentbooking",methods=['post'])
def currentbooking():
    print(request.form)
    sid=request.form['sid']
    date = request.form['date']
    qry="SELECT * FROM `booking` WHERE `date`=%s AND `s_id`=%s AND `status`='paid'"
    val=(date,sid)
    res=androidselectall(qry,val)
    print("jhj",res)
    return jsonify(res)





# app.run(host='0.0.0.0',port=5000)