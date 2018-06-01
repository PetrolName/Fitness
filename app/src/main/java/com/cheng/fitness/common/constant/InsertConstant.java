package com.cheng.fitness.common.constant;

import com.cheng.fitness.model.CommentBean;
import com.cheng.fitness.model.CommunityBean;
import com.cheng.fitness.model.CourseBean;
import com.cheng.fitness.utils.GreenDaoUtil;
import com.cheng.fitness.utils.SystemUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * author: PengCheng
 * time: 2018/5/24 0024
 * desc: 插入数据
 */

public class InsertConstant {
    public static void insertCourses() {
        List<CourseBean> beans = new ArrayList<>();

        CourseBean bean1 = new CourseBean();
        bean1.setId(1L);
        bean1.setName("适应性腹部训练");
        bean1.setCategory("健身入门");
        bean1.setExercisePosition("腹部");
        bean1.setHasAppliance(false);
        bean1.setDetail("对腹肌进行全方位的唤醒刺激，强化腹肌线条的同时加核心力量。包括动作：抬腿卷腹，仰卧剪式抬腿，侧卧提髋，卷腹，平板支撑，登山。拉伸：腹部拉伸");
        bean1.setStrength("低");
        bean1.setTime(5);
        bean1.setTvExpend(52);
        beans.add(bean1);

        CourseBean bean2 = new CourseBean();
        bean2.setId(2L);
        bean2.setName("0基础训练");
        bean2.setCategory("健身入门");
        bean2.setExercisePosition("臀部，心肺");
        bean2.setHasAppliance(false);
        bean2.setDetail("通过其强度的功能性训练，帮助零运动经验，疏于运动的人群回复运动能力，唤醒身体。包括动作：浅蹲，仰卧提髋，跳跃击掌，跪姿俯卧撑，水平深蹲，动感抬膝，仰卧抬腿。拉伸：臀部拉伸");
        bean2.setStrength("低");
        bean2.setTime(8);
        bean2.setTvExpend(81);
        beans.add(bean2);

        CourseBean bean3 = new CourseBean();
        bean3.setId(3L);
        bean3.setName("翘臀计划入门版");
        bean3.setCategory("健身入门");
        bean3.setExercisePosition("臀部");
        bean3.setHasAppliance(false);
        bean3.setDetail("通过多维度的臀肌刺激，帮助你尽快找到臀部发力的感觉。包括动作：浅蹲，仰卧提髋，跪姿后抬腿，水平深蹲。拉伸：臀部拉伸");
        bean3.setStrength("低");
        bean3.setTime(8);
        bean3.setTvExpend(89);
        beans.add(bean3);

        CourseBean bean4 = new CourseBean();
        bean4.setId(4L);
        bean4.setName("快速燃脂");
        bean4.setCategory("高效燃脂");
        bean4.setExercisePosition("心肺，腹部");
        bean4.setHasAppliance(false);
        bean4.setDetail("一次高效的燃脂体验。包括动作：登山，高抬腿跑，跳跃击掌，立卧撑跳。");
        bean4.setStrength("中");
        bean4.setTime(4);
        bean4.setTvExpend(54);
        beans.add(bean4);

        CourseBean bean5 = new CourseBean();
        bean5.setId(5L);
        bean5.setName("全身循环刺激");
        bean5.setCategory("高效燃脂");
        bean5.setExercisePosition("臀腿，腹部");
        bean5.setHasAppliance(false);
        bean5.setDetail("随时随地，想练就练，在一次活动中激活全身，是你的不二之选。包括动作：水平深蹲，标准俯卧撑，保加利亚深蹲，坐姿曲臂撑，弓步行走，立卧撑跳，前弓步，后背滚动，抬腿卷腹。拉伸：腹部拉伸，手臂拉伸，臀部拉伸");
        bean5.setStrength("高");
        bean5.setTime(17);
        bean5.setTvExpend(287);
        beans.add(bean5);

        CourseBean bean6 = new CourseBean();
        bean6.setId(6L);
        bean6.setName("热力弓步");
        bean6.setCategory("高效燃脂");
        bean6.setExercisePosition("臀腿，心肺");
        bean6.setHasAppliance(false);
        bean6.setDetail("高强度训练让你在短时间内消耗大量能量，腿部训练，帮助你快速燃脂。热身，包括动作：保加利亚深蹲，侧臀拉伸，高抬腿跑，跳跃击掌，原地蛙跳，梅森转体，锯式平板支撑。拉伸：全腿拉伸，大腿后侧拉伸");
        bean6.setStrength("高");
        bean6.setTime(18);
        bean6.setTvExpend(305);
        beans.add(bean6);

        GreenDaoUtil.saveCourses(beans);

    }

    public static void insertCommunities() {
        List<CommunityBean> beans = new ArrayList<>();

        CommunityBean bean1 = new CommunityBean();
        bean1.setName("LUVDUT");
        bean1.setContent("清晨的阳光真好！大家起床了吗？一起来慢跑吧！");
        bean1.setTime("2018.5.21");
        bean1.setComment(0);
        bean1.setImage("image1");
        bean1.setLike(5);
        beans.add(bean1);

        CommunityBean bean2 = new CommunityBean();
        bean2.setName("索拉卡");
        bean2.setContent("加油加油，坚持就是胜利，休息期间切点猕猴桃嘻嘻。");
        bean2.setTime("2018.5.28");
        bean2.setComment(1);
        bean2.setImage("image2");
        bean2.setLike(3);
        beans.add(bean2);

        CommunityBean bean3 = new CommunityBean();
        bean3.setName("易小天");
        bean3.setContent("今日打卡，在外出差也不能停下来锻炼的脚步！就是这么任性，就是要成为一个自律的人。我感觉到自己离练成漂亮的腹肌越来越近了。");
        bean3.setTime("2018.5.27");
        bean3.setComment(2);
        bean3.setImage("image3");
        bean3.setLike(5);
        beans.add(bean3);

        CommunityBean bean4 = new CommunityBean();
        bean4.setName("琪琪要成为型男");
        bean4.setContent("夏天越来越近了，我再也不是冬天的那个胖子了，再也不是！");
        bean4.setTime("2018.5.28");
        bean4.setComment(3);
        bean4.setImage("image4");
        bean4.setLike(8);
        beans.add(bean4);

        GreenDaoUtil.saveCommunities(beans);

        List<CommentBean> commentBeanList = new ArrayList<>();
        //评论
        CommentBean commentBean1 = new CommentBean();
        commentBean1.setAvatar("avatar1");
        commentBean1.setName("Abby");
        commentBean1.setCommunityId(bean2.getId());
        commentBean1.setContent("棒棒哒！加油！");
        commentBean1.setDate(SystemUtil.getDate());
        commentBeanList.add(commentBean1);

        CommentBean commentBean2 = new CommentBean();
        commentBean2.setAvatar("avatar2");
        commentBean2.setName("Alice");
        commentBean2.setCommunityId(bean3.getId());
        commentBean2.setContent("自律的人越来越强大！");
        commentBean2.setDate(SystemUtil.getDate());
        commentBeanList.add(commentBean2);

        CommentBean commentBean3 = new CommentBean();
        commentBean3.setAvatar("avatar3");
        commentBean3.setName("Angell");
        commentBean3.setCommunityId(bean3.getId());
        commentBean3.setContent("有腹肌的男人最性感");
        commentBean3.setDate(SystemUtil.getDate());
        commentBeanList.add(commentBean3);

        CommentBean commentBean4 = new CommentBean();
        commentBean4.setAvatar("avatar2");
        commentBean4.setName("Alice");
        commentBean4.setCommunityId(bean4.getId());
        commentBean4.setContent("胖子都是潜力股");
        commentBean4.setDate(SystemUtil.getDate());
        commentBeanList.add(commentBean4);

        CommentBean commentBean5 = new CommentBean();
        commentBean5.setAvatar("avatar3");
        commentBean5.setName("Angell");
        commentBean5.setCommunityId(bean4.getId());
        commentBean5.setContent("苗条穿什么都好看");
        commentBean5.setDate(SystemUtil.getDate());
        commentBeanList.add(commentBean5);

        CommentBean commentBean6 = new CommentBean();
        commentBean6.setAvatar("avatar1");
        commentBean6.setName("Abby");
        commentBean6.setCommunityId(bean4.getId());
        commentBean6.setContent("一起加油！一起变瘦！");
        commentBean6.setDate(SystemUtil.getDate());
        commentBeanList.add(commentBean6);

        GreenDaoUtil.saveComments(commentBeanList);

    }
}
