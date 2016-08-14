var encoding = 'utf-8';
var spath = "http://project.ourgo.biz/ourgo/s/"
var defaultTag = '20120623';
	
KISSY.config({
    packages:[
        {
            name: 'extends',
            tag: defaultTag,
			path: spath,
			charset: encoding
		}, {
            name: 'gallery',
            tag: defaultTag,
			path: spath,
			charset: encoding
		}
		, {
            name: 'ds',
            tag: defaultTag,
			path: spath,
			charset: encoding
		},{
			name: 'editor',
            tag: defaultTag,
			path: spath,
			charset: encoding
		}
	],
	map:[
          [/(.+extends\/.+)-min.js(\?[^?]+)?$/, "$1.js$2"]
    ]
});

KISSY.Config.debug=true