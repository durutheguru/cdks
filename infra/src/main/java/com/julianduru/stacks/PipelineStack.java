package com.julianduru.stacks;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.pipelines.CodePipeline;
import software.amazon.awscdk.pipelines.CodePipelineSource;
import software.amazon.awscdk.pipelines.ShellStep;
import software.constructs.Construct;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * created by Julian Dumebi Duru on 02/07/2023
 */
public class PipelineStack extends Stack {


    public PipelineStack(final Construct scope, final String id) {
        this(scope, id, null);
    }


    public PipelineStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        Map<String, String> envMap = new HashMap<>();
        envMap.put("AWS_ACCESS_KEY_ID", "ASIAQ3HQNYFSV5KPGDHB");
        envMap.put("AWS_SECRET_ACCESS_KEY", "7Obbd2OmpCuaDmqQ0wboaXwLR2bG9zXclQIO7rXa");
        envMap.put("AWS_DEFAULT_REGION", "us-east-1");
        envMap.put("AWS_SESSION_TOKEN", "IQoJb3JpZ2luX2VjEHQaCXVzLWVhc3QtMSJHMEUCICJa92bEJ1/O2Bnr6IapJ0NK7U/rN/mYozpju7SAzD3UAiEAiy2HpBeRYIZ7+9fXkPlgaQISjqN7tb1QzJdETNI9sgQq/AII3P//////////ARACGgwwNTg0ODYyNzY0NTMiDPhqaMkTBxiRM8xxzyrQAiVyaFm5F2X7bp19K4hgUuKEMcYiJKtzyVO9CZNU9hY1+IdRWnDVTQtwZGMX2A/dtZSihXPqE6iM/5CYdSnWDesOdbf2nb/aHO2A1HvePBpVlzrjLR6J/5tWs4/GXTnRSWFjLskjTcEdxxffMw/4p9S7SVnUSyrU9n9PH4PN9id+bFKXOCVWjUfgSSwO5pDEUdYHhlDZof1jbx7hVkKFn5zQ9eFnsx02HU4Cgm1ibPDipEU6gF2mrX2i+lEtCE7bceZTt+hZ+H1ZG1ALpr69mJHc6UVWfp/IRSDg4rUn/kJlXW7RM5QWuIK7QNxvbPEYLX5pWY53cR9cjCvxc0bWXbb+wuZGki0e6Cd7DUKF4dCcIrTY7yzEvQZ/OPaEthr2yY+OTMdynjDjR8cnO9CcHv7HYLyllUYYj9w1ngnfZOow0OoNmD4bCBWit/XNAtxmIDC7m4elBjqnAZGhPOaL8Pe/3J1pBftZBL36HSnxkP6MQvnZHf4g+AJD31+q/NHGf/xFEezuXq9TGDm9572ytwrRQKVuKSB4VyaHTGTCL3G8CaAFdTdy+rH172YJ56DYncFY2aJDqxnPl0VtFA0m9Klc2nWqD9IqXVt2NRX8GQrQBHrbIpBQBC3Tzg523ftGfDLhFUthjRfSsLAIop/rvzkGWN36Rd9N6Z7NiMVvCwvu");

        CodePipeline pipeline = CodePipeline.Builder.create(this, "pipeline")
            .pipelineName("Pipeline")
            .synth(
                ShellStep.Builder.create("Synth")
                    .input(
                        CodePipelineSource.gitHub("durutheguru/cdks", "main")
                    )
                    .commands(
                        Arrays.asList(
                            "npm install -g aws-cdk",
                            "cd infra",
                            "aws configure set aws_access_key_id $AWS_ACCESS_KEY_ID",
                            "aws configure set aws_secret_access_key $AWS_SECRET_ACCESS_KEY",
                            "aws configure set region $AWS_DEFAULT_REGION",
                            "aws configure set aws_session_token $AWS_SESSION_TOKEN",
                            "cat ~/.aws/config || true",
                            "cat ~/.aws/credentials || true",
                            "aws sts get-caller-identity || true",
                            "cdk deploy --all --profile default"
                        )
                    )
                    .primaryOutputDirectory("infra/cdk.out")
                    .env(envMap)
                    .build()
            )
            .build();
    }

}


