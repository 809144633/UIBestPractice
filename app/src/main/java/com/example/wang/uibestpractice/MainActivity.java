package com.example.wang.uibestpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Msg> msgList=new ArrayList<>();
    private EditText input_text;
    private Button send;
    private RecyclerView msgRV;
    private MsgAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input_text=findViewById(R.id.input_text);
        send=findViewById(R.id.send);
        msgRV=findViewById(R.id.msg_recyclerview);
        adapter=new MsgAdapter(msgList);
        initMsg();
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        msgRV.setLayoutManager(layoutManager);
        msgRV.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content=input_text.getText().toString();
                if(!"".equals(content)){
                    Msg msg=new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size()-1);
                    msgRV.scrollToPosition(msgList.size()-1);
                    input_text.setText("");
                }
            }
        });
    }

    private void initMsg() {
        Msg msg1=new Msg("Hello guy!",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2=new Msg("Hi!",Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3=new Msg("What's up?",Msg.TYPE_SENT);
        msgList.add(msg3);
        Msg msg4=new Msg("Fine.",Msg.TYPE_RECEIVED);
        msgList.add(msg4);
    }
}
