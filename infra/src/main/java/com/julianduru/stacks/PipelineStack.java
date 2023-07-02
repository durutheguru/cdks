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
        envMap.put("AWS_ACCESS_KEY_ID", "ASIAQ3HQNYFSS3XQRHGC");
        envMap.put("AWS_SECRET_ACCESS_KEY", "CPMSg5MLcdPRJXTZ7ujVV5S8N/WloYSbgpb1/FQ5");

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
                            "cat ~/.aws/config",
                            "aws sts get-caller-identity",
                            "export AWS_ACCESS_KEY_ID=ASIAQ3HQNYFSS3XQRHGC",
                            "export AWS_SECRET_ACCESS_KEY=CPMSg5MLcdPRJXTZ7ujVV5S8N/WloYSbgpb1/FQ5",
                            "cdk deploy --all"
                        )
                    )
                    .primaryOutputDirectory("infra/cdk.out")
                    .env(envMap)
                    .build()
            )
            .build();
    }

}


