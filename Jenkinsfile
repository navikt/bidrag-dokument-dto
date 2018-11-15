node {
    def commitHash, commitHashShort, commitUrl
        def repo = "navikt"
        def color
        def application = "bidrag-dokument-dto"
        def committer, committerEmail, pom, changelog, releaseVersion, isSnapshot, nextVersion, amount // metadata
        def mvn = "/usr/bin/mvn"

        stage("#1: checkout code") {
            cleanWs()
                withCredentials([string(credentialsId: 'OAUTH_TOKEN', variable: 'token')]) {
                    withEnv(['HTTPS_PROXY=http://webproxy-utvikler.nav.no:8088']) {
                        sh(script: "git clone https://${token}:x-oauth-basic@github.com/${repo}/${application}.git .")
                }
                }
        }

    stage("#2: initialize") {
        pom = readMavenPom file: 'pom.xml'
            releaseVersion = pom.version.tokenize("-")[0]
            tokens = releaseVersion.tokenize(".")
            devVersion = "${tokens[0]}.${tokens[1]}"
            isSnapshot = pom.version.contains("-SNAPSHOT")
            committer = sh(script: 'git log -1 --pretty=format:"%an (%ae)"', returnStdout: true).trim()
            committerEmail = sh(script: 'git log -1 --pretty=format:"%ae"', returnStdout: true).trim()
            changelog = sh(script: 'git log `git describe --tags --abbrev=0`..HEAD --oneline', returnStdout: true)
            commitHash = sh(script: 'git rev-parse HEAD', returnStdout: true).trim()
            commitHashShort = sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
            commitUrl = "https://github.com/${repo}/${application}/commit/${commitHash}"
            amount = env.BUILD_NUMBER.toString().padLeft(4,'0')
            releaseVersion = "${devVersion}.${amount}-SNAPSHOT"
            imageVersion = "${releaseVersion}"
            newReleaseVersion = amount
    }

    stage("#3: Verify maven dependency versions") {
        sh 'echo "Verifying that no snapshot dependencies is being used."'
            sh 'grep module pom.xml | cut -d">" -f2 | cut -d"<" -f1 > snapshots.txt'
            sh 'while read line;do if [ "$line" != "" ];then if [ `grep SNAPSHOT $line/pom.xml | wc -l` -gt 1 ];then echo "SNAPSHOT-dependencies found. See file $line/pom.xml.";exit 1;fi;fi;done < snapshots.txt'
    }

    stage("#4: Build & Test Project") {
        sh "mkdir -p /tmp/${application}"
            if (isSnapshot) {
                sh "${mvn} clean install -Djava.io.tmpdir=/tmp/${application} -B -e"
            } else {
                println("POM version is not a SNAPSHOT, it is ${pom.version}. Skipping build and testing of backend")
            }
    }

    stage("#5: release & deploy") {
        withCredentials([string(credentialsId: 'OAUTH_TOKEN', variable: 'token')]) {
                    withEnv(['HTTPS_PROXY=http://webproxy-utvikler.nav.no:8088']) {
                        sh "${mvn} versions:set -DremoveSnapshot=true -Djava.io.tmpdir=/tmp/${application} -B"
                        sh "${mvn} clean deploy -Djava.io.tmpdir=/tmp/${application} -B"
                        sh "${mvn} release:prepare release:perform -Dusername=d142796 -Dpassword=${token} -Djava.io.tmpdir=/tmp/${application} -B"
            }
        }
    }
}
