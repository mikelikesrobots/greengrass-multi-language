# Greengrass Multi Language

This repository contains packages that can be built into Greengrass components.

1. java-mqtt-hello: Java package for publishing an MQTT message every second
2. python-mqtt-hello: Python package for publishing an MQTT message every second

## Building

### Dependencies

Java, Python, and Maven are required to build the packages in this repository. On Ubuntu, these can be installed using:

```bash
sudo apt install -y default-jre maven python3 python3-pip
```

All packages recommend building via the [Greengrass Development Kit](https://docs.aws.amazon.com/greengrass/v2/developerguide/greengrass-development-kit-cli.html). It can be installed using:

```bash
pip3 install git+https://github.com/aws-greengrass/aws-greengrass-gdk-cli.git@v1.6.1
```

Finally, the AWS CLI must be installed and configured with valid credentials. See [here](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html) for more details.

### Configure Packages

To build each package successfully, locate its `gdk-config.json` and modify the author, version, publish bucket, and publish region. The publish bucket does not need to be a unique name, as the GDK will automatically append information to the bucket name unique to the account.

### Build Packages

With dependencies installed, go into each package and execute:

```bash
gdk component build
```

## Test

The packages can be deployed locally on a machine already running Greengrass. This step is automated using the `scripts/local_deploy.sh` located in each package. For example:

```bash
cd java-mqtt-hello
./scripts/local_deploy.sh
```

The MQTT Test Client in the AWS Console can then be used to check for incoming messages.

## Publish

Each component can be published using `gdk`:

```bash
gdk component publish
```
