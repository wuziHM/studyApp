package com.example.test.myapplicationtest.home.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.test.myapplicationtest.R;
import com.example.test.myapplicationtest.dto.GroupUserDto;
import com.example.test.myapplicationtest.dto.MateriaDto;
import com.example.test.myapplicationtest.dto.ResTeacherDto;
import com.example.test.myapplicationtest.home.adapter.ResMateriaAdapter;
import com.example.test.myapplicationtest.home.adapter.ResTeachAdapter;
import com.example.test.myapplicationtest.service.ResourceService;
import com.example.test.myapplicationtest.service.StuAndTecService;
import com.facebook.drawee.view.SimpleDraweeView;

public class StuManageActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_reback;

    private TextView tv_plan;

    private TextView tv_status;

    private  TextView tv_comment;

    private TextView tv_notes;

    private TextView tv_res;

    private SimpleDraweeView teacher_photo;
    private SimpleDraweeView adviser_photo;
    private SimpleDraweeView student_photo;

    private StuAndTecService mStuAndTecService;

    private GroupUserDto result = new GroupUserDto();

    private Handler mHandler = new Handler (){
        public void handleMessage(Message msg){
            switch(msg.what){//根据message做逻辑处理
                /*获取t讨论组成员完成*/
                case StuAndTecService.GETGROUPUSER:
                    result = (GroupUserDto) msg.obj;
                    //加载圆形图片
                    student_photo.setImageURI(Uri.parse(result.getHeadImg ()));
                    teacher_photo.setImageURI (Uri.parse(result.getMasterImg ()));
                    adviser_photo.setImageURI ( Uri.parse(result.getAdviserImg ()) );
//                    result.getAdviserImg ();
//                    result.getHeadImg ();
//                    result.getMasterImg ();
                    break;
            }
            super.handleMessage(msg);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_stu_manage );
        mStuAndTecService = new StuAndTecService(this);
        mStuAndTecService.setHandler ( mHandler);
        initView();
        loadGroupUser();
    }

    /**
     * 获取讨论组成员
     */
    private void loadGroupUser() {
        new Thread(){
            @Override
            public void run() {
                mStuAndTecService.getGroupUser ( 38 );

            }
        }.start();

    }

    private void initView() {
        tv_reback = (TextView) findViewById ( R.id.tv_reback );

        student_photo = (SimpleDraweeView)findViewById ( R.id.student_photo );
        teacher_photo = (SimpleDraweeView)findViewById ( R.id.teacher_photo );
        adviser_photo = (SimpleDraweeView)findViewById ( R.id.adviser_photo);

        tv_comment = (TextView) findViewById ( R.id.tv_comment );
        tv_notes = (TextView) findViewById ( R.id.tv_notes );
        tv_res = (TextView) findViewById ( R.id.tv_res );
        tv_status = (TextView) findViewById ( R.id.tv_status );
        tv_plan = (TextView) findViewById ( R.id.tv_plan );

        tv_reback.setOnClickListener ( this );

        tv_comment.setOnClickListener ( this );
        tv_notes.setOnClickListener ( this );
        tv_res.setOnClickListener ( this );
        tv_status.setOnClickListener ( this );
        tv_plan.setOnClickListener ( this );

    }

    public void onClick(View v){
        switch (v.getId ()) {
            case R.id.tv_reback:
                this.finish ();
                break;
            case R.id.tv_comment:
                Intent intent = new Intent ( this, CommentActivity.class );
                this.startActivity ( intent );
                break;
            case R.id.tv_plan:
                Intent intent_plan = new Intent ( this, TeachPlanActivity.class );
                this.startActivity ( intent_plan );
                break;
            case R.id.tv_notes:
                Intent intent_notes = new Intent ( this, StuNotesActivity.class );
                this.startActivity ( intent_notes );
                break;
            case R.id.tv_res:
                Intent intent_res = new Intent ( this, StuResActivity.class );
                this.startActivity ( intent_res );
                break;
            case R.id.tv_status:
                Intent intent_status = new Intent ( this, StuStatusActivity.class );
                this.startActivity ( intent_status );
                break;
            default:
                break;
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
