/**
*auth:阿尔萨斯
*date:2013-07-13
*function:创建公共的弹出窗口
*Dialog：普通弹出窗口(带遮罩层、确认、取消按钮)
*Dialog_Normal:无按钮弹出窗口
*Dialog_Notitle:无标题栏
*/
var Dialog = function(){
    var options = arguments[0] || {};
    this.title = options.title || "新窗口",
    this.width = options.width || 400,
    this.height = options.height || 300,
    this.container = document.createElement("div"),
    this.maskdiv = document.createElement("div"),
    this.id = "id" + Math.abs(new Date() * Math.random()).toFixed(0);
    this.init();
  }
  Dialog.prototype = {
    constructor: Dialog,
    init: function() {
      var me = this,
      container = me.container,
      width = me.width, 
      height = me.height,
      id = me.id,
      builder = [],
      t = "getElementsByTagName",
      bg = function(pic){
        var bgcolor = arguments[0] || 'transparent',
        left = arguments[1] || 'left',
        s =  'background:'+bgcolor+";"+left+' center;';
        return s;
      };
      if(typeof Dialog.z === "undefined"){
        Dialog.zIndex = 999;
      }
      document.body.insertBefore(container,null);
      document.body.insertBefore(me.maskdiv,null);
      me.maskdiv.id="maskdiv";
      container.id = id;
      container.className = "popups";
      builder.push('<div class="box"></div>');
      builder.push('<div id="caption" class="caption">'+me.title+'</div>');
      builder.push('<div class="form"><div class="replaceable"></div>');
      builder.push('<div class="submitable">');
      builder.push('<a class="negative" href="javascript:void(0)">取消</a>');
      builder.push('<a class="positive" href="javascript:void(0)">确认</a>');
      builder.push('</div></div>');
      builder.push('<a class="closebtn" href="javascript:void(0)"></a>');
      container.innerHTML = builder.join('');
      var size  = me.getBrowserWindowSize();
      me.left = ((size.width - width)/2) >> 0;
      me.top = ((size.height - height)/2) >> 0;
      me.ie6 = /msie|MSIE 6/.test(navigator.userAgent);
      var divs = container[t]("div"),k = divs.length;
      while (--k >= 0) {
        if(divs[k].className == "replaceable"){
          me.content = divs[k]
          break;
        }
      }
      //设置样式
      me.css(".box", "background-color:#515151; -webkit-border-radius:5px;-moz-border-radius:5px;"+
      	"border-radius:5px;FILTER: alpha(opacity=40);opacity: 0.4;-moz-opacity: 0.4;position:absolute;z-index:999;"+
      	"width:"+width+"px;height:"+height+"px;")
      me.css(".popups","position:absolute;width:"+width+"px;height:"+
        height+"px;left:"+me.left+"px;top:"+me.top+"px;z-index:1000");//background:#68DFFB
      container.style.zIndex = Dialog.zIndex++;
      me.css(".popups .caption",'top:10px;left:10px;-webkit-border-top-right-radius:5px;-webkit-border-top-left-radius:5px;'+
      	'-moz-border-top-right-radius:5px;-moz-border-top-left-radius:5px;'+
      	'border-top-right-radius:5px;border-top-left-radius:5px;position:absolute;width:'+(width-20)+'px;height:25px;'+
        'font:700 14px/20px "SimSun","Times New Roman";z-index:1000;color: #000;'+
        bg("#d2cdce","5px"));
      me.css(".popups .closebtn",'position:absolute;top:10px;right:10px;display:block;width:28px; '+
        'height:17px;text-decoration:none;'+ bg("o_dialog_closebtn.gif"));
      me.css(".popups a.closebtn:hover",bg("o_dialog_closebtn_over.gif"));
      me.css(".form","top:10px;left:10px;-webkit-border-top-right-radius:5px;-webkit-border-bottom-left-radius:5px;"+
      	"-moz-border-bottom-right-radius:5px;-moz-border-bottom-left-radius:5px;"+
      	"border-bottom-right-radius:5px;border-bottom-left-radius:5px;z-index:1000;position:absolute;top:35px;width:"+(width-20)+"px;height:"+(height-45)+"px;background:#fff;");
      me.css(".popups .submitable","-webkit-border-top-right-radius:5px;-webkit-border-bottom-left-radius:5px;"+
      	"-moz-border-bottom-right-radius:5px;-moz-border-bottom-left-radius:5px;"+
      	"border-bottom-right-radius:5px;border-bottom-left-radius:5px;position:absolute;bottom:0;border-top:1px solid #c0c0c0;width:100%;height:40px;background:#f9f9f9;");
      var buttoncss = 'display:block;float:right;margin: 0.7em 0.5em;padding:2px 7px;border:1px solid #dedede;'
        + 'background:#f5f5f5;color:#a9ea00;text-decoration:none;';
      me.css("a.positive",buttoncss);//IE6有bug，不能动态创建联合选择器
      me.css("a.negative",buttoncss);
      me.css("a.negative","color:#ff5e00;");
      me.css("a.positive:hover","border:1px solid #E6EFC2;background:#E6EFC2;color:#529214;");
      me.css("a.negative:hover","border:1px solid #fbe3e4;background:#fbe3e4;color:#d12f19;");
      me.css("a.positive:active","border:1px solid #529214;background:#529214;color:#fff;");
      me.css("a.negative:active","border:1px solid #d12f19;background:#d12f19;color:#fff;");
      me.css("a","outline: 0;");
      //按钮的圆角
      var ff = /a/[-1]=='a';
        me.css("a.positive","border-radius:4px;");
        me.css("a.negative","border-radius:4px;");
      if(ff){
        me.css("a.positive","-moz-border-radius:4px;");
        me.css("a.negative","-moz-border-radius:4px;");
      }else{
        me.css("a.positive","-webkit-border-radius:4px;");
        me.css("a.negative","-webkit-border-radius:4px;");
      }
      //***************************IE6 弹出窗口中遮不住select******************************
      if(me.ie6){
        me.iframe = document.createElement("<iframe style='position:absolute;left:"+
          me.left+"px;top:"+me.top+"px;width:"+(me.width+10)+"px;height:"+
          (me.height+10)+"px;z-index:"+(Dialog.zIndex-2)+";filter:mask();display:none;' ></iframe>");
        container.insertAdjacentElement('beforeBegin',me.iframe);
      }
      //*****************************监听点击**************************
      container.onclick = function(){
        var ee = me.getEvent(), node = ee[1],tag = ee[2];
        if(tag == "a" ){
          switch(node.className){
            case "closebtn" :
              me.hide(me.id);
              break;
            case "positive" :
              me.submit();
              break;
            case "negative" :
              me.hide(me.id);
              break;
          }
        }
      }
      var caption = document.getElementById("caption");
      caption.onmousedown = function(e){
        e = e || window.event;
        container.offset_x = e.clientX - container.offsetLeft;
        container.offset_y = e.clientY - container.offsetTop;
        document.onmousemove = function(e){
          me.drag(e,me)
        }
        document.onmouseup = function(){
          me.dragend(container)
        }
      }
    },
    drag:function(e,me){
      e = e || window.event;//获得事件对象
      var el = me.container;
      var l = e.clientX - el.offset_x  + "px",
      t = e.clientY - el.offset_y  + "px";
      with(el.style){
        left=l;
        top=t;
        cursor="move"
      }
      if(me.ie6){
        with(me.iframe.style){
          left=l;
          top=t;
        }
      }
        !+"v1"? document.selection.empty() : window.getSelection().removeAllRanges();
    },
    dragend:function(el){
      el.style.cursor = "";
      document.onmouseup = document.onmousemove = null;
    },
    hide : function(id){
      this.container.style.display = "none" ;
      if(this.ie6){
        this.iframe.style.display = "none";
      }
      document.body.style.overflow = "";
	  //窗口关闭时，通知页面删除当前div，避免表单重复不能提交问题
	  document.body.removeChild(document.getElementById(id));
	  document.body.removeChild(document.getElementById("maskdiv"));
    },
    submit:function(){
    	//确定按钮提交，根据需要，在自己的js中覆盖此方法
    },
    show : function(){
      this.container.style.display = "block" ;
      if(this.ie6){
        this.iframe.style.display = "block";
      }
      var size = this.getBrowserWindowSize();
      var de = document.documentElement;
      this.mode();
    },
    getBrowserWindowSize :function(){
      var de = document.documentElement;
      return {
        width: (de.clientWidth || document.body.clientWidth),
        height:(de.clientHeight || document.body.clientHeight)
      }
    },
    attr: function(node,bag){
      for(var i in bag){
        if(bag.hasOwnProperty(i))
          node.setAttribute(i,bag[i])
      }
    },
    getEvent : function(e) {
      e = e || window.event;
      if (!e) {
        var c = this.getEvent.caller;
        while (c) {
          e = c.arguments[0];
          if (e && (Event == e.constructor || MouseEvent  == e.constructor)) {
            break;
          }
          c = c.caller;
        }
      }
      var target = e.srcElement ? e.srcElement : e.target,
      currentN = target.nodeName.toLowerCase(),
      parentN  = target.parentNode.nodeName.toLowerCase(),
      grandN = target.parentNode.parentNode.nodeName.toLowerCase();
      return [e,target,currentN,parentN,grandN];
    },
    mode:function(w,h){
      var mask = document.getElementById("maskdiv");
      mask.setAttribute("style", "position:absolute;top:0%;left:0%;width:100%;height:100%;background-color:#dedede;-moz-opacity:0.7;opacity:.70;filter: alpha(opacity=70);");
	  document.body.style.overflow = "hidden";
    },
    stopEvent:function(e){
      e = e || window.event;
      if(e.preventDefault) {
        e.preventDefault();
        e.stopPropagation();
      }else{
        e.returnValue = false;
        e.cancelBubble = true;
      }
    },
    incss:function(node,bag){
      var str = ";"
      for(var i in bag){
        if(bag.hasOwnProperty(i))
          str += i+":"+bag[i]+";"
      }
      node.style.cssText = str;
    },
    css:function(selector,declaration){
      if(typeof document.createStyleSheet === 'undefined') {
        document.createStyleSheet = (function() {
          function createStyleSheet() {
            var element = document.createElement('style');
            element.type = 'text/css';
            document.getElementsByTagName('head')[0].appendChild(element);
            var sheet = document.styleSheets[document.styleSheets.length - 1];
            if(typeof sheet.addRule === 'undefined')
              sheet.addRule = function(selectorText, cssText, index) {
                if(typeof index === 'undefined')
                  index = this.cssRules.length;
                this.insertRule(selectorText + ' {' + cssText + '}', index);
              };
            return sheet;
          }
          return createStyleSheet;
        })();
      }
      if(!!Dialog.sheet){
          Dialog.sheet.addRule(selector,declaration);
      }else{
        Dialog.sheet = document.createStyleSheet();
        Dialog.sheet.addRule(selector,declaration);
        Dialog.mask = document.createElement("div");
        document.body.insertBefore(Dialog.mask,this.container);
      }
    }
  };
  
  
  
  /****************************无按钮弹出窗口***************************************/
  var Dialog_Normal = function(){
    var options = arguments[0] || {};
    this.title = options.title || "",
    this.width = options.width || 400,
    this.height = options.height || 300,
    this.container = document.createElement("div"),
    this.maskdiv = document.createElement("div"),
    this.id = "id" + Math.abs(new Date() * Math.random()).toFixed(0);
    this.init();
  }
  Dialog_Normal.prototype = {
    constructor: Dialog_Normal,
    init: function() {
      var me = this,
      container = me.container,
      width = me.width, 
      height = me.height,
      id = me.id,
      builder = [],
      t = "getElementsByTagName",
      bg = function(pic){
        var bgcolor = arguments[0] || 'transparent',
        left = arguments[1] || 'left',
        s =  'background:'+bgcolor+";"+left+' center;';
        return s;
      };
      if(typeof Dialog_Normal.z === "undefined"){
        Dialog_Normal.zIndex = 999;
      }
      document.body.insertBefore(container,null);
      document.body.insertBefore(me.maskdiv,null);
      me.maskdiv.id="maskdiv";
      container.id = id;
      container.className = "popups";
      builder.push('<div class="box"></div>');
      builder.push('<div class="caption">'+me.title+'</div>');
      builder.push('<div class="form"><div class="replaceable"></div>');
      builder.push('</div>');
      builder.push('<a class="closebtn" href="javascript:void(0)"></a>');
      container.innerHTML = builder.join('');
      var size  = me.getBrowserWindowSize();
      me.left = ((size.width - width)/2) >> 0;
      me.top = ((size.height - height)/2) >> 0;
      me.ie6 = /msie|MSIE 6/.test(navigator.userAgent);
      var divs = container[t]("div"),k = divs.length;
      while (--k >= 0) {
        if(divs[k].className == "replaceable"){
          me.content = divs[k]
          break;
        }
      }
      //设置样式
      me.css(".box", "background-color:#515151; -webkit-border-radius:5px;-moz-border-radius:5px;"+
      	"border-radius:5px;FILTER: alpha(opacity=40);opacity: 0.4;-moz-opacity: 0.4;position:absolute;z-index:999;"+
      	"width:"+width+"px;height:"+height+"px;")
      me.css(".popups","position:absolute;width:"+width+"px;height:"+
        height+"px;left:"+me.left+"px;top:"+me.top+"px;z-index:1000");//background:#68DFFB
      container.style.zIndex = Dialog_Normal.zIndex++;
      me.css(".popups .caption",'top:10px;left:10px;-webkit-border-top-right-radius:5px;-webkit-border-top-left-radius:5px;'+
      	'-moz-border-top-right-radius:5px;-moz-border-top-left-radius:5px;'+
      	'border-top-right-radius:5px;border-top-left-radius:5px;position:absolute;width:'+(width-20)+'px;height:25px;'+
        'font:700 14px/20px "SimSun","Times New Roman";z-index:1000;color: #000;'+
        bg("#d2cdce","5px"));
      me.css(".popups .closebtn",'position:absolute;top:10px;right:10px;display:block;width:28px; '+
        'height:17px;text-decoration:none;'+ bg("o_dialog_closebtn.gif"));
      me.css(".popups a.closebtn:hover",bg("o_dialog_closebtn_over.gif"));
      me.css(".form","top:10px;left:10px;-webkit-border-top-right-radius:5px;-webkit-border-bottom-left-radius:5px;"+
      	"-moz-border-bottom-right-radius:5px;-moz-border-bottom-left-radius:5px;"+
      	"border-bottom-right-radius:5px;border-bottom-left-radius:5px;z-index:1000;position:absolute;top:35px;width:"+(width-20)+"px;height:"+(height-45)+"px;background:#fff;");

      //***************************IE6 弹出窗口中遮不住select******************************
      if(me.ie6){
        me.iframe = document.createElement("<iframe style='position:absolute;left:"+
          me.left+"px;top:"+me.top+"px;width:"+(me.width+10)+"px;height:"+
          (me.height+10)+"px;z-index:"+(Dialog_Normal.zIndex-2)+";filter:mask();display:none;' ></iframe>");
        container.insertAdjacentElement('beforeBegin',me.iframe);
      }
    },
    drag:function(e,me){
      e = e || window.event;//获得事件对象
      var el = me.container;
      var l = e.clientX - el.offset_x  + "px",
      t = e.clientY - el.offset_y  + "px";
      with(el.style){
        left=l;
        top=t;
        cursor="move"
      }
      if(me.ie6){
        with(me.iframe.style){
          left=l;
          top=t;
        }
      }
        !+"v1"? document.selection.empty() : window.getSelection().removeAllRanges();
    },
    dragend:function(el){
      el.style.cursor = "";
      document.onmouseup = document.onmousemove = null;
    },
    hide : function(id){
      this.container.style.display = "none" ;
      if(this.ie6){
        this.iframe.style.display = "none";
      }
      document.body.style.overflow = "";
	  //窗口关闭时，通知页面删除当前div，避免表单重复不能提交问题
	  document.body.removeChild(document.getElementById(this.id));
	  document.body.removeChild(document.getElementById("maskdiv"));
    },
    show : function(){
      this.container.style.display = "block" ;
      if(this.ie6){
        this.iframe.style.display = "block";
      }
      var size = this.getBrowserWindowSize();
      var de = document.documentElement;
      this.mode(de.scrollLeft + de.clientWidth, de.scrollHeight);
    },
    getBrowserWindowSize :function(){
      var de = document.documentElement;
      return {
        width: (de.clientWidth || document.body.clientWidth),
        height:(de.clientHeight || document.body.clientHeight)
      }
    },
    attr: function(node,bag){
      for(var i in bag){
        if(bag.hasOwnProperty(i))
          node.setAttribute(i,bag[i])
      }
    },
    getEvent : function(e) {
      e = e || window.event;
      if (!e) {
        var c = this.getEvent.caller;
        while (c) {
          e = c.arguments[0];
          if (e && (Event == e.constructor || MouseEvent  == e.constructor)) {
            break;
          }
          c = c.caller;
        }
      }
      var target = e.srcElement ? e.srcElement : e.target,
      currentN = target.nodeName.toLowerCase(),
      parentN  = target.parentNode.nodeName.toLowerCase(),
      grandN = target.parentNode.parentNode.nodeName.toLowerCase();
      return [e,target,currentN,parentN,grandN];
    },
    mode:function(w,h){
      var mask = document.getElementById("maskdiv");
      mask.setAttribute("style", "position:absolute;top:0%;left:0%;width:100%;height:100%;background-color:#dedede;-moz-opacity:0.7;opacity:.70;filter: alpha(opacity=70);");
	  document.body.style.overflow = "hidden";
    },
    stopEvent:function(e){
      e = e || window.event;
      if(e.preventDefault) {
        e.preventDefault();
        e.stopPropagation();
      }else{
        e.returnValue = false;
        e.cancelBubble = true;
      }
    },
    incss:function(node,bag){
      var str = ";"
      for(var i in bag){
        if(bag.hasOwnProperty(i))
          str += i+":"+bag[i]+";"
      }
      node.style.cssText = str;
    },
    css:function(selector,declaration){
      if(typeof document.createStyleSheet === 'undefined') {
        document.createStyleSheet = (function() {
          function createStyleSheet() {
            var element = document.createElement('style');
            element.type = 'text/css';
            document.getElementsByTagName('head')[0].appendChild(element);
            var sheet = document.styleSheets[document.styleSheets.length - 1];
            if(typeof sheet.addRule === 'undefined')
              sheet.addRule = function(selectorText, cssText, index) {
                if(typeof index === 'undefined')
                  index = this.cssRules.length;
                this.insertRule(selectorText + ' {' + cssText + '}', index);
              };
            return sheet;
          }
          return createStyleSheet;
        })();
      }
      if(!!Dialog_Normal.sheet){
      	
          Dialog_Normal.sheet.addRule(selector,declaration);
      }else{
        Dialog_Normal.sheet = document.createStyleSheet();
        Dialog_Normal.sheet.addRule(selector,declaration);
        Dialog_Normal.mask = document.createElement("div");
        document.body.insertBefore(Dialog_Normal.mask,this.container);
      }
    }
  };
  
  
  /****************************无标题栏弹出窗口***************************************/
  var Dialog_Notitle = function(){
    var options = arguments[0] || {};
    this.title = options.title || "",
    this.width = options.width || 400,
    this.height = options.height || 300,
    this.container = document.createElement("div"),
    this.maskdiv = document.createElement("div"),
    this.id = "id" + Math.abs(new Date() * Math.random()).toFixed(0);
    this.init();
  }
  Dialog_Notitle.prototype = {
    constructor: Dialog_Notitle,
    init: function() {
      var me = this,
      container = me.container,
      width = me.width, 
      height = me.height,
      id = me.id,
      builder = [],
      t = "getElementsByTagName",
      bg = function(pic){
        var bgcolor = arguments[0] || 'transparent',
        left = arguments[1] || 'left',
        s =  'background:'+bgcolor+";"+left+' center;';
        return s;
      };
      if(typeof Dialog_Notitle.z === "undefined"){
        Dialog_Notitle.zIndex = 999;
      }
      document.body.insertBefore(container,null);
      document.body.insertBefore(me.maskdiv,null);
      me.maskdiv.id="maskdiv";
      container.id = id;
      container.className = "popups";
      builder.push('<div class="box"></div>');
      builder.push('<div class="caption">'+me.title+'</div>');
      builder.push('<div class="form"><div class="replaceable"></div>');
      builder.push('</div>');
      builder.push('<a class="closebtn" href="javascript:void(0)"></a>');
      container.innerHTML = builder.join('');
      var size  = me.getBrowserWindowSize();
      me.left = ((size.width - width)/2) >> 0;
      me.top = ((size.height - height)/2) >> 0;
      me.ie6 = /msie|MSIE 6/.test(navigator.userAgent);
      var divs = container[t]("div"),k = divs.length;
      while (--k >= 0) {
        if(divs[k].className == "replaceable"){
          me.content = divs[k]
          break;
        }
      }
      //设置样式
      me.css(".box", "background-color:#515151; -webkit-border-radius:5px;-moz-border-radius:5px;"+
      	"border-radius:5px;FILTER: alpha(opacity=40);opacity: 0.4;-moz-opacity: 0.4;position:absolute;z-index:999;"+
      	"width:"+width+"px;height:"+height+"px;")
      me.css(".popups","position:absolute;width:"+width+"px;height:"+
        height+"px;left:"+me.left+"px;top:"+me.top+"px;z-index:1000");//background:#68DFFB
      container.style.zIndex = Dialog_Notitle.zIndex++;
      me.css(".popups .caption",'top:10px;left:10px;-webkit-border-top-right-radius:5px;-webkit-border-top-left-radius:5px;'+
      	'-moz-border-top-right-radius:5px;-moz-border-top-left-radius:5px;'+
      	'border-top-right-radius:5px;border-top-left-radius:5px;position:absolute;width:'+(width-20)+'px;height:25px;'+
        'font:700 14px/20px "SimSun","Times New Roman";z-index:1000;color: #000;'+
        bg("#fff","5px"));
      me.css(".popups .closebtn",'position:absolute;top:10px;right:10px;display:block;width:28px; '+
        'height:17px;text-decoration:none;'+ bg("o_dialog_closebtn.gif"));
      me.css(".popups a.closebtn:hover",bg("o_dialog_closebtn_over.gif"));
      me.css(".form","top:10px;left:10px;-webkit-border-top-right-radius:5px;-webkit-border-bottom-left-radius:5px;"+
      	"-moz-border-bottom-right-radius:5px;-moz-border-bottom-left-radius:5px;"+
      	"border-bottom-right-radius:5px;border-bottom-left-radius:5px;z-index:1000;position:absolute;top:35px;width:"+(width-20)+"px;height:"+(height-45)+"px;background:#fff;");

      //***************************IE6 弹出窗口中遮不住select******************************
      if(me.ie6){
        me.iframe = document.createElement("<iframe style='position:absolute;left:"+
          me.left+"px;top:"+me.top+"px;width:"+(me.width+10)+"px;height:"+
          (me.height+10)+"px;z-index:"+(Dialog_Notitle.zIndex-2)+";filter:mask();display:none;' ></iframe>");
        container.insertAdjacentElement('beforeBegin',me.iframe);
      }
    },
    drag:function(e,me){
      e = e || window.event;//获得事件对象
      var el = me.container;
      var l = e.clientX - el.offset_x  + "px",
      t = e.clientY - el.offset_y  + "px";
      with(el.style){
        left=l;
        top=t;
        cursor="move"
      }
      if(me.ie6){
        with(me.iframe.style){
          left=l;
          top=t;
        }
      }
        !+"v1"? document.selection.empty() : window.getSelection().removeAllRanges();
    },
    dragend:function(el){
      el.style.cursor = "";
      document.onmouseup = document.onmousemove = null;
    },
    hide : function(id){
      this.container.style.display = "none" ;
      if(this.ie6){
        this.iframe.style.display = "none";
      }
      document.body.style.overflow = "";
	  //窗口关闭时，通知页面删除当前div，避免表单重复不能提交问题
	  document.body.removeChild(document.getElementById(this.id));
	  document.body.removeChild(document.getElementById("maskdiv"));
    },
    show : function(){
      this.container.style.display = "block" ;
      if(this.ie6){
        this.iframe.style.display = "block";
      }
      var size = this.getBrowserWindowSize();
      var de = document.documentElement;
      this.mode(de.scrollLeft + de.clientWidth, de.scrollHeight);
    },
    getBrowserWindowSize :function(){
      var de = document.documentElement;
      return {
        width: (de.clientWidth || document.body.clientWidth),
        height:(de.clientHeight || document.body.clientHeight)
      }
    },
    attr: function(node,bag){
      for(var i in bag){
        if(bag.hasOwnProperty(i))
          node.setAttribute(i,bag[i])
      }
    },
    getEvent : function(e) {
      e = e || window.event;
      if (!e) {
        var c = this.getEvent.caller;
        while (c) {
          e = c.arguments[0];
          if (e && (Event == e.constructor || MouseEvent  == e.constructor)) {
            break;
          }
          c = c.caller;
        }
      }
      var target = e.srcElement ? e.srcElement : e.target,
      currentN = target.nodeName.toLowerCase(),
      parentN  = target.parentNode.nodeName.toLowerCase(),
      grandN = target.parentNode.parentNode.nodeName.toLowerCase();
      return [e,target,currentN,parentN,grandN];
    },
    mode:function(w,h){
      var mask = document.getElementById("maskdiv");
      mask.setAttribute("style", "position:absolute;top:0%;left:0%;width:100%;height:100%;background-color:#dedede;-moz-opacity:0.7;opacity:.70;filter: alpha(opacity=70);");
	  document.body.style.overflow = "hidden";
    },
    stopEvent:function(e){
      e = e || window.event;
      if(e.preventDefault) {
        e.preventDefault();
        e.stopPropagation();
      }else{
        e.returnValue = false;
        e.cancelBubble = true;
      }
    },
    incss:function(node,bag){
      var str = ";"
      for(var i in bag){
        if(bag.hasOwnProperty(i))
          str += i+":"+bag[i]+";"
      }
      node.style.cssText = str;
    },
    css:function(selector,declaration){
      if(typeof document.createStyleSheet === 'undefined') {
        document.createStyleSheet = (function() {
          function createStyleSheet() {
            var element = document.createElement('style');
            element.type = 'text/css';
            document.getElementsByTagName('head')[0].appendChild(element);
            var sheet = document.styleSheets[document.styleSheets.length - 1];
            if(typeof sheet.addRule === 'undefined')
              sheet.addRule = function(selectorText, cssText, index) {
                if(typeof index === 'undefined')
                  index = this.cssRules.length;
                this.insertRule(selectorText + ' {' + cssText + '}', index);
              };
            return sheet;
          }
          return createStyleSheet;
        })();
      }
      if(!!Dialog_Notitle.sheet){
      	
          Dialog_Notitle.sheet.addRule(selector,declaration);
      }else{
        Dialog_Notitle.sheet = document.createStyleSheet();
        Dialog_Notitle.sheet.addRule(selector,declaration);
        Dialog_Notitle.mask = document.createElement("div");
        document.body.insertBefore(Dialog_Notitle.mask,this.container);
      }
    }
  };