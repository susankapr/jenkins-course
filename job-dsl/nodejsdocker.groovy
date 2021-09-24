job('NodeJS Docker example') {
    scm {
        git('git://github.com/susankapr/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('susankapr')
            node / gitConfigEmail('susan.kapr@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('susankapr/docker-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
