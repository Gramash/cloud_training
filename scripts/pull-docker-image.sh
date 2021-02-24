echo $REPOSITORY_URI
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 866515130898.dkr.ecr.us-east-1.amazonaws.com/cloud-training
docker pull 866515130898.dkr.ecr.us-east-1.amazonaws.com/cloud-training:latest