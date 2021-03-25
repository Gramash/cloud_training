- **Module 1**
1. In order to run application locally do the following: 
 - execute in terminal:
    1. docker build -t spring-boot-kotlin .
    2. docker run -p 8080:8080 spring-boot-kotlin
- access the app via http://localhost:8080/actuator/info url to check the app is running
2. Url to access application running in aws-cloud 
    - http://ec2-54-175-188-47.compute-1.amazonaws.com:8080/actuator/info

- **Module 2** 

To check the 2nd module i've created another account with limited access to CI/CD pipeline
Please follow these steps to run the pipeline and watch it:
1. Access http://ec2-54-175-188-47.compute-1.amazonaws.com:8080/api/health-check to see the initial message

2. Sign in to the console and access CodePipeline dashboard
- Go to [aws.amazon.com](https://aws.amazon.com) and click orange button **'Sign in to the Console'** in the top right corner
- Choose *IAM Role* and use the credentials from the email to sign in
- Once you've signed in type **'CodePipeline'** in the search box on top of the window and access the CodePipeline Service
- You should be able to see only one pipeline called **'cloud-training-pipeline'**. Click it to access its stages
3. Trigger the pipeline  
- clone the project from github and checkout on develop branch
- change the value for **hello.message property** inside *src/main/resources/view/localization/messages.properties* file.
- Pushing these changes to origin will trigger the pipeline automatically as you will be able to in the CodePipeline dashboard
4. Check the pipeline
- Once the pipeline has finished you can click on **details** in the *unit-test* stage and find a tab called **Reports**
to check unit-tests reports
- access the http://ec2-54-175-188-47.compute-1.amazonaws.com:8080/api/health-check endpoint. 
You should see the message you've pushed there instead on the one from the 0. step

Thank You! 
