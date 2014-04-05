//************************************************
//版本:v1.0
//by:liuyh83 一旧饭
//************************************************
(function ($) {
    function Location() {
        this.xmldoc = null;
        this.provinceDDl = null;
        this.cityDDl = null;
        this.districtDDl = null;
        this._defaults = {
            xmlpatch: "Location.xml",//城市地区XML路径
            xdis: true,//是否一直显视城市和地区的选择框
            firstop: true //是否显视选择框的默认选择
        };
    }

    $.extend(Location.prototype, {
        _attachLocation: function (target, options) {
            var settings = $.extend(this._defaults, options);
            var xmldoc = this._getXmldocument(settings.xmlpatch);
            var lo = this;
            this.xmldoc = xmldoc;
            this.provinceDDl = $("<select id=province/>");
            this.cityDDl = $("<select id=city/>");
            this.districtDDl = $("<select id=district/>");
            this.provinceDDl.change(function () {
                lo._cityPross(xmldoc);
            });
            this.cityDDl.change(function () {
                lo._districtPross(xmldoc);
            });
            this._provincePross(xmldoc);

            this.provinceDDl.appendTo("#" + $(target).attr("id"));
            this.cityDDl.appendTo("#" + $(target).attr("id"));
            this.districtDDl.appendTo("#" + $(target).attr("id"));

        },
        _provincePross: function (xmldoc) {
            provinces = xmldoc.getElementsByTagName("Province");
            this.provinceDDl.empty();
            if (this._defaults.firstop == true) {
                this.provinceDDl.append($("<option/>", { val: '0', text: "选择省份" }));
            }

            for (var i = 0; i < provinces.length; i++) {
                this.provinceDDl.append($("<option/>", { val: provinces[i].getAttribute("ProvinceId"), text: provinces[i].getAttribute("ProvinceName") }));

            }
            this._cityPross(xmldoc);

        },
        _cityPross: function (xmldoc) {
            this.cityDDl.empty();
            if (this._defaults.firstop == true) {
                this.cityDDl.append($("<option/>", { val: '0', text: "选择城市" }));
            }

            do {
                var i = 0;
                for (i = 0; i < provinces.length; i++) {
                    if (provinces[i].getAttribute("ProvinceId") == this.provinceDDl.val()) {
                        break;
                    }

                }



                var citys = new Array();
                if (this.provinceDDl.val() != 0) {
                    citys = provinces[i].getElementsByTagName("City");
                }


                if (this._defaults.xdis || citys.length > 0 ) {
                    this.cityDDl.css("display", "inline");
                }
                else {
                    this.cityDDl.css("display", "none");
                }


                if (citys.length == 0) {
                    break;
                }


                for (var j = 0; j < citys.length; j++) {
                    this.cityDDl.append($("<option/>", { val: citys[j].getAttribute("CityId"), text: citys[j].getAttribute("CityName") }));
                }
            } while (false);

            this._districtPross(xmldoc);
        },
        _districtPross: function (xmldoc) {
            this.districtDDl.empty();
            if (this._defaults.firstop == true) {
                this.districtDDl.append($("<option/>", { val: '0', text: "选择地区" }));
            }
            do {
                var i = 0;
                for (i = 0; i < provinces.length; i++) {
                    if (provinces[i].getAttribute("ProvinceId") == this.provinceDDl.val()) {
                        break;
                    }

                }

                var citys = new Array();
                if (this.provinceDDl.val() != 0) {
                    citys = provinces[i].getElementsByTagName("City");
                }

                //                if (citys.length == 0) {
                //                    break;
                //                }

                var j = 0;
                for (j = 0; j < citys.length; j++) {
                    if (citys[j].getAttribute("CityId") == this.cityDDl.val()) {
                        break;
                    }
                }


                var districts = new Array();
                if (this.cityDDl.val() != 0) {
                    districts=citys[j].getElementsByTagName("District");
                }




                if (this._defaults.xdis || districts.length > 0) {
                    this.districtDDl.css("display", "inline");
                }
                else {
                    this.districtDDl.css("display", "none");
                }

                if (districts.length == 0) {

                    break;
                }


                for (var x = 0; x < districts.length; x++) {
                    this.districtDDl.append($("<option/>", { val: districts[x].getAttribute("DistrictId"), text: districts[x].getAttribute("DistrictName") }));
                }
            } while (false);
        },
        _getXmldocument: function (ld_url) {
            if (navigator.userAgent.indexOf("Opera") > -1) {
                ld_url += ("?seed=" + Math.random());
            };

            var lo_xmlhttp = this._createXmlhttp();
            if (!lo_xmlhttp) {
                return null;
            }

            lo_xmlhttp.open("GET", ld_url, false);
            lo_xmlhttp.send(null);

            var doc = "";
            if (lo_xmlhttp.status == 200) {
                doc = lo_xmlhttp.responseXML.documentElement;
                return doc;
            }
            return null;
        },
        _createXmlhttp: function () {
            var lo_http_request = false;
            if (window.ActiveXObject) {
                //var ld_xmlhttps=["MSXML2.XMLHTTP.5.0","MSXML2.XMLHTTP.4.0","MSXML2.XMLHTTP.3.0","MSXML2.XMLHTTP","Microsoft.XMLHTTP"];
                var ld_xmlhttps = ["MSXML2.XMLHTTP.4.0", "MSXML2.XMLHTTP.3.0", "MSXML2.XMLHTTP", "Microsoft.XMLHTTP"];
                for (var l_i = 0; l_i < ld_xmlhttps.length; l_i++) {
                    try {
                        lo_http_request = new ActiveXObject(ld_xmlhttps[l_i]);
                        break;
                    }
                    catch (ld_e) {
                        lo_http_request = false;
                    };
                };
            }
            else {
                try {
                    lo_http_request = new XMLHttpRequest();
                }
                catch (ld_e) {
                    lo_http_request = false;
                };
            };
            return lo_http_request;
        }
    });


    $.fn.location = function (options) {
        return this.each(function () {
            window["location_" + $(this).attr("id")] = new Location();
            var location = window["location_" + $(this).attr("id")];
            location._attachLocation(this, options);
        });
    };

    $.extend($.fn, {
        GetProvinceObj: function () { return window["location_" + $(this).attr("id")].provinceDDl; },//获取省份选择框的对像
        GetProvinceVal: function () { return window["location_" + $(this).attr("id")].provinceDDl.val(); },//获取省份选择框的val属性
        GetCityObj: function () { return window["location_" + $(this).attr("id")].cityDDl; },//获取城市选择框的对像
        GetCityVal: function () { return window["location_" + $(this).attr("id")].cityDDl.val(); },//获取城市选择框的val属性
        GetDistrictObj: function () { return window["location_" + $(this).attr("id")].districtDDl; },//获取地区选择框的对像
        GetDistrictVal: function () { return window["location_" + $(this).attr("id")].districtDDl.val(); },//获取地区选择框的val属性
        GetProvinceTxt: function () { return window["location_" + $(this).attr("id")].provinceDDl.find("option:selected").text(); },//获取省份选择框的Text属性
        GetCityTxt: function () { return window["location_" + $(this).attr("id")].cityDDl.find("option:selected").text(); },//获取城市选择框的Text属性
        GetDistrictTxt: function () { return window["location_" + $(this).attr("id")].districtDDl.find("option:selected").text(); },//获取地区选择框的Text属性
        SetProvinceVal: function (v) { window["location_" + $(this).attr("id")].provinceDDl.val(v); },
        SetCityVal: function (v) { window["location_" + $(this).attr("id")].cityDDl.val(v); },
        SetDistrictVal: function (v) { window["location_" + $(this).attr("id")].districtDDl.val(v); },
        SetProvinceIndex: function (v) { window["location_" + $(this).attr("id")].provinceDDl.get(0).selectedIndex = v; },
        SetCityIndex: function (v) { window["location_" + $(this).attr("id")].cityDDl.get(0).selectedIndex = v; },
        SetDistrictIndex: function (v) { window["location_" + $(this).attr("id")].districtDDl.get(0).selectedIndex = v; }
    });



})(jQuery);   
