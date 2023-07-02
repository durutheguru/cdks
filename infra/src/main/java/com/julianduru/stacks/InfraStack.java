package com.julianduru.stacks;

import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.s3.Bucket;
import software.constructs.Construct;


public class InfraStack extends Stack {


    public InfraStack(final Construct scope, final String id) {
        this(scope, id, null);
    }


    public InfraStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);


        Bucket.Builder.create(this, "CDKBucket8239843982179234")
            .bucketName("CDKBucket8239843982179234")
            .versioned(true)
            .removalPolicy(RemovalPolicy.DESTROY)
            .autoDeleteObjects(true)
            .build();
    }


}


