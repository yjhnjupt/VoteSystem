package com.example.demo.controller;

import com.example.demo.entity.Result;
import com.example.demo.entity.Select;
import com.example.demo.entity.Vote;
import com.example.demo.entity.voteResult;
import com.example.demo.mapper.VoteMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/vote")
public class VoteController {
    @Autowired
    VoteMapper voteMapper;
    //获取投票信息
    @ApiOperation("查询功能")
    @GetMapping("/{name}")
    public Result getVote(@PathVariable String name){

        List<Vote> list_vote=voteMapper.getVote(name);
        List<voteResult> list_voteResult= new ArrayList<>();

        for(int i=0;i<list_vote.size();i++){
            int id=list_vote.get(i).getId();
            String username=list_vote.get(i).getUsername();
            String title=list_vote.get(i).getTitle();
            String describe=list_vote.get(i).getDescribe();
            String words=list_vote.get(i).getSelection();
            words= words.replaceAll("\n", "");
            String[] mark = words.split("<%");
            List<Select> list_select=new ArrayList<>();
            for (int j = 1; j <= mark.length; j++) {
                if(j%3==0){
                    int num=Integer.parseInt(mark[j-2]);
                    int count=Integer.parseInt(mark[j-1]);
                    String selectionText=mark[j];
                    Select select_1=new Select(num,count,selectionText);
                    list_select.add(select_1);
                }
            }
            voteResult voteResult_1=new voteResult(id,username,title, describe, list_select);
            list_voteResult.add(voteResult_1);
        }
        return new Result<>(200,"查询成功",list_voteResult);
    }
    //新增投票信息

    @ApiOperation("创建投票信息")
    @PostMapping("/add")
    public Result addVote(@RequestBody voteResult voteResult_1){

        List<Select> selects=voteResult_1.getSelect();
        String selection = "";
        for(int i=0;i<selects.size();i++){
                selection+= "<%" + selects.get(i).getNum() + "<%" + selects.get(i).getCount() + "<%" + selects.get(i).getSelectionText();
        }
        voteMapper.insertVote(voteResult_1.getUsername(),voteResult_1.getTitle(),voteResult_1.getDescribe(),selection);
        Integer[] s=voteMapper.getMaxId();
        int maxId=s[0];
        for(int i=0;i<s.length;i++){
            if(maxId<s[i]){
                maxId=s[i];
            }
        }
        Vote vote_new=voteMapper.checkVote(voteResult_1.getUsername(),maxId);
        int id=vote_new.getId();
        String username=vote_new.getUsername();
        String title=vote_new.getTitle();
        String describe=vote_new.getDescribe();
        String words=vote_new.getSelection();
        words= words.replaceAll("\n", "");
        String[] mark = words.split("<%");
        List<Select> list_select=new ArrayList<>();
        for (int j = 1; j <= mark.length; j++) {
            if(j%3==0){
                int num=Integer.parseInt(mark[j-2]);
                int count=Integer.parseInt(mark[j-1]);
                String selectionText=mark[j];
                Select select_1=new Select(num,count,selectionText);
                list_select.add(select_1);
            }
        }
        voteResult voteResult_2=new voteResult(id,username,title, describe, list_select);
        return new Result<>(200,"添加成功",voteResult_2);
    }

    //用户投票
    @ApiOperation("用户投票")
    @PostMapping("/submit")
    public Result editVote(@RequestBody voteResult voteResult_2){

        Vote vote_old=voteMapper.checkVote(voteResult_2.getUsername(),voteResult_2.getId());
        String words_old=vote_old.getSelection();

        List<Select> selects=voteResult_2.getSelect();
        String selection = "";
        for(int i=0;i<selects.size();i++){
            selection+= "<%" + selects.get(i).getNum() + "<%" + selects.get(i).getCount() + "<%" + selects.get(i).getSelectionText();
        }
        String words_new=selection;
        List<Map<String, String>> selectionList = new ArrayList<Map<String, String>>();
        words_old = words_old.replaceAll("\n", "");
        words_new = words_new.replaceAll("\n", "");
        String[] mark_old = words_old.split("<%");
        String[] mark_new = words_new.split("<%");


        for (int i = 1; i < mark_old.length; i++) {
            Map<String, String> selectMap = new HashMap<String, String>();
            selectMap.put("num", mark_old[i]);
            ++i;
            int count_old=Integer.parseInt(mark_old[i]);
            int count_new=count_old+Integer.parseInt(mark_new[i]);
            String count_new_string=Integer.toString(count_new);
            selectMap.put("count",count_new_string);
            ++i;
            selectMap.put("Text", mark_old[i]);
            selectionList.add(selectMap);
        }

        String selectionText = "";
        for (Map<String, String> map : selectionList) {
            selectionText += "<%" + map.get("num") + "<%" + map.get("count") + "<%" + map.get("Text");
        }

        voteMapper.updateVote(selectionText,voteResult_2.getUsername(),voteResult_2.getId());
        Vote vote_new=voteMapper.checkVote(voteResult_2.getUsername(),voteResult_2.getId());

        int id=vote_new.getId();
        String username=vote_new.getUsername();
        String title=vote_new.getTitle();
        String describe=vote_new.getDescribe();
        String words=vote_new.getSelection();
        words= words.replaceAll("\n", "");
        String[] mark = words.split("<%");
        List<Select> list_select=new ArrayList<>();
        for (int j = 1; j <= mark.length; j++) {
            if(j%3==0){
                int num=Integer.parseInt(mark[j-2]);
                int count=Integer.parseInt(mark[j-1]);
                String selectionText_2=mark[j];
                Select select_1=new Select(num,count,selectionText_2);
                list_select.add(select_1);
            }
        }
        voteResult voteResult_3=new voteResult(id,username,title, describe, list_select);
        return new Result<>(200,"修改成功",voteResult_3);
    }
    ///删除
    @ApiOperation("删除投票表单")
    @GetMapping("/delete")
    public Result deleteVote(@RequestParam( "username") String name,@RequestParam("id") Integer id){

        voteMapper.deleteVote(name,id);
        return new Result<>(200,"删除成功");
    }


}
