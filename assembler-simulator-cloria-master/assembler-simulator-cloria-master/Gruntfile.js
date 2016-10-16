module.exports = function(grunt) {

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        concat: {
            options: {
                separator: ';'
            },
            dist: {
                src: ['src/app.js', 'src/**/*.js'],
                dest: 'assets/<%= pkg.name %>.js'
            }
        },
        uglify: {
            options: {
                banner: '/*! <%= pkg.name %> <%= grunt.template.today("dd-mm-yyyy") %> */\n'
            },
            dist: {
                files: {
                    'assets/<%= pkg.name %>.min.js': ['<%= concat.dist.dest %>']
                }
            }
        },
        jshint: {
            files: ['Gruntfile.js', 'src/**/*.js'],
            options: {
                // options here to override JSHint defaults
				//cambio, pase de trailing: true, a trailing: false
				//¿Qué es trailing whitespace? -> Que venga con espacios en blanco
                trailing: false,
                globals: {
                    browser: true,
                    console: true
                }
            }
        },
        watch: {
            files: ['<%= jshint.files %>'],
            tasks: ['jshint', 'concat']
        }
    });

    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-concat');

    grunt.registerTask('default', ['jshint', 'concat', 'uglify']);

};
