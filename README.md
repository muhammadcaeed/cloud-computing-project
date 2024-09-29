# Online-Kaufen AWS Deployment

This repository contains a set of JavaScript scripts for deploying and managing the "Online-Kaufen" application on AWS. The application is packaged as a WAR file and deployed on a Tomcat 8 server.

## Scripts

* **createImage.js:** Creates an Amazon Machine Image (AMI) from a running EC2 instance.
    * Takes the instance ID as a command-line argument.
    * Creates an AMI with specified block device mappings, description, and name.
    * Uses the `config.json` file for AWS credentials and region.

* **createInstance.js:** Creates an EC2 instance.
    * Launches an instance with a specified AMI ID, instance type, key name, security groups, and user data.
    * The user data installs Java, Tomcat, downloads the WAR file and configuration files from S3, and starts Tomcat.
    * Tags the instance with the name "Online-Kaufen-Dev".
    * Uses the `config.json` file for AWS credentials and region.

* **deleteEverything.js:** Deletes all resources created for the application.
    * Deletes the Auto Scaling group, launch configuration, load balancer, target group, CloudWatch alarms, AMI, snapshot, and EBS volume.
    * Takes the load balancer ARN, target group ARN, AMI ID, snapshot ID, and volume ID as command-line arguments.
    * Uses the `config.json` file for AWS credentials and region.

* **executePolicy.js:** Executes a scaling policy for the Auto Scaling group.
    * Takes the policy name as a command-line argument.
    * Executes the specified policy with the option to honor cooldown.
    * Uses the `config.json` file for AWS credentials and region.

* **stopInstance.js:** Stops an EC2 instance and creates an AMI from it.
    * Takes the instance ID as a command-line argument.
    * Stops the instance and waits for it to stop.
    * Creates an AMI with specified block device mappings, description, and name.
    * Uses the `config.json` file for AWS credentials and region.

* **demo.js:** Creates the complete infrastructure for the application.
    * Creates a load balancer, target group, listener, Auto Scaling launch configuration, Auto Scaling group, scaling policies, and CloudWatch alarms.
    * Attaches the target group to the Auto Scaling group.
    * Takes the AMI ID as a command-line argument.
    * Uses the `config.json` file for AWS credentials and region.

## Prerequisites

* An AWS account with necessary permissions.
* AWS CLI installed and configured.
* Node.js and npm installed.
* A `config.json` file with your AWS credentials and region.
* The "Online-Kaufen" WAR file and configuration files uploaded to an S3 bucket.

## Usage

1. Clone the repository.
2. Install the dependencies: `npm install aws-sdk`
3. Update the `config.json` file with your AWS credentials and region.
4. Update the scripts with your specific resource names, ARNs, IDs, etc.
5. Run the scripts in the following order:

```bash
node createInstance.js <instance-id>
node stopInstance.js <instance-id>
node demo.js <ami-id>
node executePolicy.js <policy-name>
node deleteEverything.js <load-balancer-arn> <target-group-arn> <ami-id> <snapshot-id> <volume-id>
```