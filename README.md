
Instructions to install and run the REST client:

(I have used Eclipse, so instructions are more according to Eclipse IDE)

1.It is Maven project, so import the project and resolve the maven dependencies. 

In Eclipse :
From the menu bar *File > Import > Maven > Existing Maven project > (Browse the project)*

![alt text](https://github.com/Yashad/REST-Client/blob/master/UpdateMaven.png)

2.Update the **config.properties** under *HW4/src/main/java/config/* with the git token(Update the *Authorization:token*).

3.Update the json files under *HW4/src/main/java/data/* for the POST and PATCH call according to the task's requirments.
There are four JSON files for each of the POST/PATCH calls.<br/>
Create a new reps(createRepo.json)<br/>
Create an issue(createIssue.json)<br/>
Add an assignee(addAssignee.json)<br/>
Edit a repo (editRepo.json))

Change the JSONs accordingly.(*similar to how we put json data in POSTMAN* )

For example:
To Add assignees change addAssignee.json as follow:
```
{
  "assignees": [
    "ytrived"
  ]
}
```
 
4.Run the HW4Main.java *HW4/src/main/java/HW4Main.java* as Java Application 


**Sample Output:**
![alt text](https://github.com/Yashad/REST-Client/blob/master/output1.png)
![alt text](https://github.com/Yashad/REST-Client/blob/master/output2.png)


**ScreenShot:**
![alt text](https://github.com/Yashad/REST-Client/blob/master/ScreenShot.png)


