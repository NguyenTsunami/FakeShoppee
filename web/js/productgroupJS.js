/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var srcImgCover = $('#imgMain').attr('src');
var srcImgMain = $('#imgMain').attr('src');

function mouseEnterImg(tag) {
    var src = $(tag).attr('src');
    $('#imgMain').attr('src', src);
}

function mouseLeaveImg() {
    $('#imgMain').attr('src', srcImgMain);
}

function mouseDownImg(tag) {
    var src = $(tag).attr('src');
    srcImgMain = src;
    $('#imgMain').attr('src', srcImgMain);
}

function clickBtnItem1(tag, id, img) {
    var btn_choosed = $('.btn-item-1').find('.active');

    //not choose yet
    if (btn_choosed.length === 0) {
        //change imgMain
        srcImgMain = img;
        $('#imgMain').attr('src', srcImgMain);
        //change value of input
        $('#item1').attr('value', id);
        //toggle
        $(tag).addClass('active');
    }
    //choosed 
    else {
        btn_choosed = btn_choosed[0];
        //if this is activing
        if (btn_choosed === tag) {

            //change imgMain
            srcImgMain = srcImgCover;
            $('#imgMain').attr('src', srcImgMain);
            //change value of input
            $('#item1').attr('value', null);
            //toggle
            $(tag).removeClass('active');
        }
        //if this is not activing
        else {
            //remove active of old btn
            $(btn_choosed).removeClass('active');
            //change imgMain
            srcImgMain = img;
            $('#imgMain').attr('src', srcImgMain);
            //change value of input
            $('#item1').attr('value', id);
            //toggle
            $(tag).addClass('active');
        }
    }

    updatePrice();
    updateQuan();
}

function mouseEnterBtn(src) {
    $('#imgMain').attr('src', src);
}

function mouseLeaveBtn() {
    $('#imgMain').attr('src', srcImgMain);
}

function clickBtnItem2(tag, id) {
    var btn_choosed = $('.btn-item-2').find('.active');

    //not choose yet
    if (btn_choosed.length === 0) {
        //change value of input
        $('#item2').attr('value', id);
        //toggle
        $(tag).addClass('active');
    }
    //choosed 
    else {
        btn_choosed = btn_choosed[0];
        //if this is activing
        if (btn_choosed === tag) {
            //change value of input
            $('#item2').attr('value', null);
            //toggle
            $(tag).removeClass('active');
        }
        //if this is not activing
        else {
            //remove active of old btn
            $(btn_choosed).removeClass('active');
            //change value of input
            $('#item2').attr('value', id);
            //toggle
            $(tag).addClass('active');
        }
    }

    updatePrice();
    updateQuan();
}

function minus() {
    var value = $('#input-quantity').attr('value');
    value = parseInt(value) - 1;
    if (isNaN(value) || value < 1) {
        value = 1;
    }
    $('#input-quantity').val(value);
    $('#input-quantity').attr('value', value);
}

function plus() {
    var maxVal = 0;
    var list = $('.quan-info');
    for (var i = 0; i < list.length; i++) {
        var temp = list[i];
        if ($(temp).attr('hidden') === undefined) {
            maxVal = parseInt($(temp).text());
        }
    }

    var value = $('#input-quantity').attr('value');
    value = 1 + parseInt(value);
    if (isNaN(value)) {
        value = 1;
    } else if (value > maxVal) {
        value = maxVal;
    }
    $('#input-quantity').val(value);
    $('#input-quantity').attr('value', value);
}


function updatePrice() {
    //hide all
    $('.price-info').attr('hidden', true);

    //init
    var priceID = "#price-";

    //get item1
    var item1 = $('#item1').attr('value');
    if (item1 === undefined) {
        item1 = 'null';
    }

    //get item 2
    var item2 = $('#item2').attr('value');
    if (item2 === undefined) {
        item2 = 'null';
    }
    priceID = priceID + item1 + "-" + item2;

    //show price-item1-item2
    $(priceID).attr('hidden', null);

    //
    var count = 0;
    var list = $('.price-info');
    for (var i = 0; i < list.length; i++) {
        var temp = list[i];
        if ($(temp).attr('hidden') === undefined) {
            count++;
        }
    }
    if (count === 0) {
        $('#price-null-null').attr('hidden', null);
    }
}

function updateQuan() {
    //hide all
    $('.quan-info').attr('hidden', true);

    //init
    var quanID = "#quan-";

    //get item1
    var item1 = $('#item1').attr('value');
    if (item1 === undefined) {
        item1 = 'null';
    }

    //get item 2
    var item2 = $('#item2').attr('value');
    if (item2 === undefined) {
        item2 = 'null';
    }
    quanID = quanID + item1 + "-" + item2;

    //show price-item1-item2
    $(quanID).attr('hidden', null);

    //
    var count = 0;
    var list = $('.quan-info');
    for (var i = 0; i < list.length; i++) {
        var temp = list[i];
        if ($(temp).attr('hidden') === undefined) {
            count++;
        }
    }
    if (count === 0) {
        $('#quan-null-null').attr('hidden', null);
    }
    
    //update quantity input
    $('#input-quantity').val(1);
    $('#input-quantity').attr('value', 1);
}

$(function () {
    updatePrice();
    updateQuan();
});




