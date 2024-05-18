import os

from flask import *
from werkzeug.utils import secure_filename
from android import android_bp

app=Flask(__name__)
from DBConnection import *
app.secret_key='aaa'

# Register the Android Blueprint with the URL prefix '/android'
app.register_blueprint(android_bp, url_prefix='/android')


@app.route('/')
def login():
    qry= " select * from user where type='admin' "
    res=selectones(qry)
    if res is None:
        res="no"
    else:
        res="yes"
    return  render_template('login.html',val=res)

@app.route('/logincode',methods=['post'])
def logincode():

    uname=request.form['textfield']
    pswd=request.form['textfield2']
    qry="select * from user where email=%s and password=%s and type='admin'"
    val=(uname,pswd)
    res=selectone(qry,val)
    print("response----",res)
    if res is None:
        qry="SELECT * FROM `station` WHERE `email`=%s AND `password`=%s and status='cc'"
        res=selectone(qry,val)
        if res is None:

                return  '''<script>alert("invalid username or password");
                window.location='/'</script>'''
        else:
            session['lid']=res[0]
            q="select * from station where s_id=%s"
            s=selectone(q,session['lid'])
            name = s[1]
            session['username']=name
            return render_template('st_index.html')
    elif res[12]=='admin':
        session['s_id'] = res[0]
        return  redirect("admin_home")

    else:
        return '''<script>alert("invalid username");
                window.location='/'</script>'''









@app.route('/accept_reject')
def accept_reject():
    qry="SELECT * FROM station where status='pending'"
    res=select(qry)
    return  render_template('Acept_reject_CC.html',val=res)

@app.route('/cc_accept')
def cc_accept():
    id = request.args.get('id')
    qry="Update station set status='cc' where s_id=%s"
    iud(qry,id)
    return '''<script>alert("station approved");
                    window.location='/accept_reject'</script>'''



@app.route('/cc_reject')
def cc_reject():
    id = request.args.get('id')
    qry = "Update station set status='rejected' where s_id=%s"
    iud(qry, id)
    return '''<script>alert("station rejected");
                    window.location='/accept_reject'</script>'''




@app.route('/admin_home')
def admin_home():
    return  render_template('Admin_home.html')

@app.route('/block_unblock_user')
def block_unblock_user():
    qry = " SELECT * FROM station where status='cc'"
    res = select(qry)
    return  render_template('block_unblock_user.html',val=res)

@app.route('/cc_unblock')
def cc_unblock():
    id = request.args.get('id')
    qry="Update station set status='cc' where s_id=%s"
    iud(qry,id)
    return '''<script>alert("station unblocked");
                    window.location='/block_unblock_user#about'</script>'''


@app.route('/cc_block')
def cc_block():
    id = request.args.get('id')
    qry="Update station set status='block' where s_id=%s"
    iud(qry,id)
    return '''<script>alert("station blocked");
                    window.location='/block_unblock_user#about'</script>'''












@app.route('/add_fecility')
def add_fecility():
    id=session['lid']
    qry = "SELECT * from fecilites where s_id=%s"
    val=(str(id))
    res = selectall(qry,val)
    return  render_template('cc_add_fecility.html',val=res)


@app.route('/feci_delete')
def feci_delete():
    id = request.args.get('id')
    qry = "delete from fecilites where f_id=%s"
    iud(qry, id)
    return '''<script>alert("deleted sucessfully");
                    window.location='/add_fecility#about'</script>'''



@app.route('/adding_feci',methods=['post'])
def adding_feci():
    return  render_template('cc_adding_feci.html')


@app.route('/adding_feci_code',methods=['post'])
def adding_feci_code():
    fecility = request.form['textfield']
    image=request.files['file']
    file=secure_filename(image.filename)
    image.save(os.path.join('static/ccfile/'+file))
    description = request.form['textfield2']
    qry = "insert into fecilites values(null, %s,%s,%s,%s)"
    val=(session['lid'],fecility,description,file)
    iud(qry,val)
    return '''<script>alert("Successfull");
                    window.location='/add_fecility#about'</script>'''


@app.route('/cc_home')
def cc_home():
    return  render_template('cc_home.html')

@app.route('/cc_register')
def cc_register():
    return  render_template('cc_register.html')

@app.route('/cc_register',methods=['post'])
def cc_register_code():
    name = request.form['textfield']
    place = request.form['textfield2']
    email = request.form['textfield3']
    image=request.files['file']
    file=secure_filename(image.filename)
    image.save(os.path.join('static/ccfile',file))
    phone=request.form['textfield4']
    district = request.form['textfield9']
    longitude = request.form['textfield6']
    latitude = request.form['textfield5']
    password=request.form['textfield8']


    qry = "insert into station values(null ,%s,%s,%s,%s,%s,%s,%s,%s,%s,'pending')"
    val=(name,place,phone,email,district,latitude,longitude,file,password)
    iud(qry,val)
    return '''<script>alert("Successfull");
                    window.location='/'</script>'''







@app.route('/cc_view_booking')
def cc_view_booking():
    qry = "SELECT booking.*,user.* FROM booking JOIN USER ON user.u_id=booking.u_id WHERE STATUS='paid' and s_id="+str(session['lid'])
    res = select(qry)
    return  render_template('cc_view_booking.html',val=res)


@app.route('/book_accept')
def book_accept():
    id = request.args.get('id')
    qry="Update booking set status='completed' where b_id=%s"
    iud(qry,id)
    return '''<script>alert("booking completed");
                    window.location='/cc_view_booking'</script>'''



@app.route('/book_reject')
def book_reject():
    id = request.args.get('id')
    qry = "Update booking set status='reject' where b_id=%s"
    iud(qry, id)
    return '''<script>alert("booking rejected");
                    window.location='/cc_view_booking'</script>'''




@app.route('/cc_view_feedback')
def cc_view_feedback():

    qry="SELECT `feedback`.*,`user`.* FROM `feedback` JOIN `user` ON `feedback`.u_id=`user`.u_id WHERE `feedback`.s_id=%s"
    res = selectall(qry,session['lid'])
    return  render_template('cc_view_feedback.html', val=res)


@app.route('/edit_rest_form')
def edit_rest_form():
    return  render_template('edit_rest_form.html')



@app.route('/edit_rest_form_code',methods=['post'])
def edit_rest_form_code():
    name = request.form['textfield']
    place = request.form['textfield2']
    district = request.form['select']
    phone = request.form['textfield4']
    email = request.form['textfield5']
    longitude = request.form['textfield6']
    latitude = request.form['textfield7']
    rimage=request.files['file']
    image = secure_filename(rimage.filename)
    rimage.save(os.path.join('static/restimage' + image))
    qry = "insert into restaurant values(null , %s,%s,%s,%s,%s,%s,%s,%s)"
    val = (name,place,district,latitude,longitude,phone,email,image)
    iud(qry, val)
    return '''<script>alert("Successfull");
                        window.location='/admin_home'</script>'''
    return  render_template('edit_rest_form.html')



@app.route('/send_reply')
def send_reply():
    cid=request.args.get('cid')
    session['cid']=cid

    return  render_template('send_reply.html')

@app.route('/send_reply_code', methods=['post'])
def send_reply_code():
    reply = request.form['textfield']
    qry = " update complaint set reply=%s where c_id=%s "
    val = (reply,session['cid'])
    iud(qry, val)
    return '''<script>alert("Successfully replyed");
                        window.location='/View_complaint'</script>'''


@app.route('/registration')
def registration():
    return  render_template('registration.html')


@app.route('/view_CC_fecility')
def view_CC_fecility():
    qry="select * from station"
    ress=select(qry)


    return  render_template('view_CC_fecility.html',vall=ress)

@app.route('/view_CC_fecility_code',methods=['post'])
def view_CC_fecility_code():
    center=request.form['select']
    qry = "SELECT  `station`.name,fecilites.* FROM fecilites JOIN station ON station.s_id=fecilites.s_id where fecilites.s_id=%s "
    res = selectall(qry,center)
    qry = "select * from station"
    ress = select(qry)
    return render_template('view_CC_fecility.html', val=res, vall=ress,center=str(center))








@app.route('/View_complaint')
def View_complaint():
    qry = " SELECT complaint.*,user.f_name,l_name,station.name FROM complaint JOIN user ON user.u_id=complaint.u_id JOIN station ON station.s_id=complaint.s_id WHERE complaint.reply='pending' "
    res = select(qry)
    return  render_template('View_complaint.html',val=res)

@app.route('/view_feedback')
def view_feedback():
    qry ="SELECT feedback.*,user.f_name,l_name,station.name FROM feedback JOIN user ON user.u_id=feedback.u_id JOIN station ON station.s_id=feedback.s_id  "
    res = select(qry)
    return  render_template('view_feedback.html',val=res)

@app.route('/view_user')
def view_user():
    qry = "SELECT * from user where type ='user'"
    res = select(qry)
    return  render_template('view_user.html',val=res)

@app.route('/report')
def report():
    return  render_template('report.html',val=[],mth="")


@app.route('/report_code',methods=['post'])
def report_code():
    month = request.form['select']
    qry="SELECT COUNT(`b_id`) AS c,COUNT(*)*210 FROM `booking`  WHERE MONTH(DATE)=%s AND booking.s_id=%s"
    val=(month,session['lid'])
    res=selectone(qry,val)
    return  render_template('report.html',val=res,mth=str(month))


@app.route('/admin_registration')
def admin_registration():
        return render_template('admin_registration.html')

@app.route('/admin_register_code',methods=['post'])
def admin_register_code():
    f_name = request.form['textfield']
    l_name = request.form['textfield2']
    h_name = request.form['textfield3']
    place = request.form['textfield4']
    post = request.form['textfield5']
    pin = request.form['textfield6']
    phone=request.form['textfield9']
    email = request.form['textfield7']
    district = request.form['select']
    gender= request.form['radiobutton']
    password=request.form['textfield8']


    qry = "insert into user values(null,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,'admin')"
    val=(f_name,l_name,h_name,place,post,pin,district,gender,email,phone,password)
    iud(qry,val)
    return '''<script>alert("Successfull");
                    window.location='/'</script>'''

@app.route('/index')
def index():
    return  render_template('index.html')


app.run(debug=True)