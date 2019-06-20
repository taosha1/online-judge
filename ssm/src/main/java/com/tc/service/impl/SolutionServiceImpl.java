package com.tc.service.impl;

import com.alibaba.fastjson.JSON;
import com.tc.dao.ProblemDao;
import com.tc.dao.SolutionDao;
import com.tc.dao.UserDao;
import com.tc.dao.User_SolutionDao;
import com.tc.domain.Problem;
import com.tc.domain.Solution;
import com.tc.domain.User;
import com.tc.domain.User_Solution;
import com.tc.service.SolutionService;
import com.tc.utils.Result;
import com.tc.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Repository
public class SolutionServiceImpl implements SolutionService {
    @Autowired
    private SolutionDao solutionDao;
    @Autowired
    private ProblemDao problemDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private User_SolutionDao user_solutionDao;

    @Override
    public Result commitByPost(Solution solution, String username, Problem problem, String codeRootPath, String questionRootPath) {
        boolean isFirstCommit = false;
        String systemPath = "C:\\Users\\wang\\Desktop\\online-judge\\ssm\\target\\ssm";
        String packagePath = "language" + "/" + solution.getLanguageType() + "/" + username;
        String filename = "method" + problem.getId() + "." + solution.getLanguageType();
        String filepath = codeRootPath + packagePath;

        File dir = new File(filepath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(filepath + "/" + filename);
        System.out.println(filepath + "/" + filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
                isFirstCommit = true;
//                System.out.print(isFirstCommit);
            } catch (IOException e) {
                e.printStackTrace();
                return new Result(false, "创建文件失败！");
            }
        }

        HashMap<String, Object> testResult = new HashMap<String, Object>();
        PrintStream ps = null;

        switch (solution.getLanguageType()) {
            case "java":
                String solutionPackage = "language" + "." + solution.getLanguageType() + "." + username;
                try {
                    ps = new PrintStream(new FileOutputStream(file));
                    ps.println("package " + solutionPackage + ";");// 往文件里写入字符串
                    ps.print(solution.getCode());// 在已有的基础上添加字符串
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return new Result(false, "写入java文件失败！");
                }
//                String javaCmd1 = "javac "+filepath+"/"+filename+" -d . ";
                String javaCmd1 = "javac " + filepath + "/" + filename + " -d " + systemPath + "/build";
//                String javaCmd2 = "java "+solutionPackage+"."+"method" + problem.getId();
                String javaCmd2 = "java -cp " + systemPath + "/build" + " " + solutionPackage + "." + "method" + problem.getId();
                String javaCmds[] = new String[2];
                javaCmds[0] = javaCmd1;
                javaCmds[1] = javaCmd2;
                try {
                    System.out.println("testJavaData start");
                    testResult = Tools.testJavaData(problem, javaCmds, questionRootPath);
                    System.out.println(testResult);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return new Result(false, "测试数据失败！");
                }
//                DeleteDirectory.deleteDir(new File("language"));
                break;
            case "cpp":
                try {
                    ps = new PrintStream(new FileOutputStream(file));
                    ps.println(solution.getCode());// 在已有的基础上添加字符串
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return new Result(false, "写入cpp文件失败！");
                }

                String cppCmd1 = "g++ -o " + "method" + problem.getId() + " " + filepath + "/" + filename;
                String cppCmd2 = "./" + "method" + problem.getId();
                String cppCmds[] = new String[2];
                cppCmds[0] = cppCmd1;
                cppCmds[1] = cppCmd2;
                try {
                    testResult = Tools.testCppData(problem, cppCmds, questionRootPath);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return new Result(false, "测试数据失败！");
                }
//                DeleteDirectory.deleteDir(new File("method" + problem.getId()));
                break;
            default:
                testResult.put("defaultMsg", "没有匹配到相应的语言类型！");
                break;
        }
        solution.setState(JSON.toJSON(testResult).toString());
        solution.setCode(filepath + "/" + filename);

        User updataUser = null;
        try {
            updataUser = userDao.findByName(username);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "查询该解答的作者失败！");
        }

        if ((Boolean) testResult.get("isPass")) {
            updataUser.setPassNum(updataUser.getPassNum() + 1);
            problem.setPass(problem.getPass() + 1);
        }

        problem.setCommit(problem.getCommit() + 1);
        User_Solution user_solution = new User_Solution();
        user_solution.setUser(updataUser);
        user_solution.setSolution(solution);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        user_solution.setPostDate(df.format(new Date()));
        user_solution.setState(JSON.toJSON(testResult).toString());
        if (isFirstCommit) {
            user_solution.setTimes(1);
            Solution afterInsertSolution = null;
            int insertSolutionRow = 0;
            int insertUser_SolutionRow = 0;
            try {
                insertSolutionRow = solutionDao.addSolution(solution);
                afterInsertSolution = solutionDao.searchSolutionByCode(filepath + "/" + filename);
                user_solution.setSolution(afterInsertSolution);
                insertUser_SolutionRow = user_solutionDao.addUser_Solution(user_solution);
                problemDao.updataProblem(problem);
                userDao.updateUser(updataUser);
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(false, "数据库添加新的解答失败！");

            }
            if (insertSolutionRow == 0 || insertUser_SolutionRow == 0) {
                return new Result(false, "添加新的解答未成功！");
            } else {
                return new Result(true, JSON.toJSON(afterInsertSolution).toString());
            }
        } else {
            int updataSolutionRow = 0;
            int UpdataUser_SolutionRow = 0;
            try {
                Solution oldSolution = solutionDao.searchSolutionByCode(filepath + "/" + filename);
                solution.setId(oldSolution.getId());
                HashMap<String, Integer> userAndSolution = new HashMap<String, Integer>();
                userAndSolution.put("user_id", updataUser.getId());
                userAndSolution.put("solution_id", solution.getId());
                User_Solution oldUser_Solution = user_solutionDao.searchUser_SolutionByUserAndSolution(userAndSolution);
                user_solution.setTimes(oldUser_Solution.getTimes() + 1);
                user_solution.setId(oldUser_Solution.getId());
//                System.out.println("start updata");
                updataSolutionRow = solutionDao.updataSolutionProvider(solution);
                UpdataUser_SolutionRow = user_solutionDao.updataUser_SolutionProvider(user_solution);

//                System.out.println((boolean)JSON.parseObject(oldSolution.getState()).get("isPass"));
                if (!(boolean) JSON.parseObject(oldSolution.getState()).get("isPass")) {
                    problemDao.updataProblem(problem);
                }
                userDao.updateUser(updataUser);
//                System.out.println("updata end");

//                System.out.println("updataSolutionRow: "+updataSolutionRow);
//                System.out.println("UpdataUser_SolutionRow: "+UpdataUser_SolutionRow);

            } catch (Exception e) {
                e.printStackTrace();
                return new Result(false, "数据库更新新的解答失败！");

            }
            if (updataSolutionRow == 0 || UpdataUser_SolutionRow == 0) {
                return new Result(false, "更新新的解答未成功！");
            } else {
                System.out.println("success commit");
                return new Result(true, JSON.toJSON(solution).toString());
            }
        }

    }
}
