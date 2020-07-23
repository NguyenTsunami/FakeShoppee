/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var brandList = $('.brand-list-item');

function setBrandSelect() {
    $('.brand-list-item').remove();
    var categoryID = $('select').find('option').filter(':selected').val();
    var brandID = 'brand-' + categoryID;
    for (var i = 0; i < brandList.length; i++) {
        var brandTag = brandList[i];
        if ($(brandTag).attr('id') === brandID) {
            $(brandTag).clone().appendTo('#brand-container');
            return;
        }
    }
}

var classify_default = $('#classify-default');
var classify_group = $('.classify-group');
var div_classify_list = $('#div-classify-list');
var div_add_classify_group = $('#div-add-classify-group');
var classify_item = $('.classify-item');

function addClassifyGroup() {
    var count = $('.classify-group').length;

    //add group
    classify_group.clone().appendTo(div_classify_list);
    var temp = $('.classify-group')[count + 1 - 1];
    //change label of group
    $(temp).find('.classify-group-label').text("Classify Group " + (count + 1));
    var temp_div = $(temp).find('.div-classify-group');
    var temp_div_div = $(temp_div).find('.classify-group-name');
    var input = $(temp_div_div).find('input');
    //change attr name of input
    $(input).attr('name', "classify-group-" + (count + 1) + "-name");
    //change attr name of input of item
    var temp_div_item = $(temp_div).find('.classify-item');
    var item_input = $(temp_div_item).find('input');
    $(item_input).attr('name', "classify-" + (count + 1) + "-item-name");

    //
    if (count === 0) {
        //remove default
        $('#classify-default').remove();
        //add button add group
        div_add_classify_group.clone().appendTo(div_classify_list);
    } else if (count === 1) {
        //remove add button
        $('#div-add-classify-group').remove();
    }

    //
    loadClassifyImg();
}

function closeClassifyGroup(spanTag) {
    var div_remove = $(spanTag).closest('div');
    var div = $(div_remove).next();
    var count = $('.classify-group').length;

    //if click remove classify 1 incase there are 2 classify
    if (count === 2 && $('.classify-group:first').is(div_remove)) {
        //change label of group 2
        $(div).find('.classify-group-label').text("Classify Group 1");
        var div_div = $(div).find('.div-classify-group');
        var div_div_div = $(div_div).find('.classify-group-name');
        var input = $(div_div_div).find('input');
        //change attr name of input
        $(input).attr('name', "classify-group-1-name");
        //change attr name of input of each item
        var div_div_item = $(div_div).find('.classify-item');
        $(div_div_item).find('input').attr('name', "classify-1-item-name");
    }

    //remove group
    $(div_remove).remove();
    count = count - 1;
    //
    if (count === 1) {
        //add button add group
        div_add_classify_group.clone().appendTo(div_classify_list);
    } else if (count === 0) {
        //remove button add group
        $('#div-add-classify-group').remove();
        //add default
        classify_default.clone().appendTo(div_classify_list);
    }

    //
    loadClassifyImg();
}

function addNewClassifyItem(btnTag) {
    var div_add_classify_item = $(btnTag).parent();
    var div_classify_group = $(div_add_classify_item).parent();
    var classify_group = $(div_classify_group).parent();
    var stt = $('.classify-group:first').is(classify_group) ? 1 : 2;

    //add input item
    var temp = classify_item.clone();
    temp.find('input').attr('name', "classify-" + stt + "-item-name");
    temp.appendTo(div_classify_group);
    div_add_classify_item.clone().appendTo(div_classify_group);

    //arrange button
    var count = div_classify_group.find('.classify-item').length;
    div_classify_group.find('.div-add-classify-item')[0].remove();
    div_classify_group.find('.div-add-classify-item').find('span').text("Added (" + count + "/20)");

    //
    loadClassifyImg();
}

function readURL(input) {
    if (input.files && input.files[0]) {

        var reader = new FileReader();
        var div = $(input).closest('div');
        var img = $(div).find('img');

        reader.onload = function (e) {
            $(img).attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}

function loadDisplayImg(tag) {
    for (var i = 0; i < $(tag).length; i++) {
        var item = $(tag)[i];
        if ($(item).attr('src') === '') {
            $(item).closest('div').find('i').attr('hidden', null);
            $(item).attr('hidden', 'true');
        } else {
            $(item).attr('hidden', null);
            $(item).closest('div').find('i').attr('hidden', 'true');
        }
    }
}

var classify_images = $('#classify-images');
var item_img = $(classify_images).find('.item-img');

function loadClassifyImg() {
    var count = $('.classify-group').length;
    if (count === 0) {
        //hidden
        $(classify_images).find('label.col-3.col-form-label').attr('hidden', true);
        $(classify_images).find('.item-img').remove();
        loadDisplayImg($('.img-display'));
        return;
    }
    //show
    $(classify_images).find('label.col-3.col-form-label').attr('hidden', null);
    $(classify_images).find('.item-img').remove();

    var root = $('.div-classify-group')[0];
    //change name of label col-3
    var classifyName = $(root).find('.classify-group-name').find('input').val();
    if (classifyName === '') {
        classifyName = 'Images Of Classify';
    }
    $(classify_images).find('label').text(classifyName);

    //add item-img
    var itemList = $(root).find('.classify-item').find('input');
    var col8 = $(classify_images).find('div')[0];
    for (var i = 0; i < itemList.length; i++) {
        var itemName = $(itemList[i]).val();
        if (itemName === '') {
            itemName = 'Item Image';
        }
        var temp = item_img.clone();
        temp.find('label').text(itemName);
        temp.appendTo(col8);
    }
    loadDisplayImg($('.img-display'));
}


var table_type_1 = $('#table-type-1');
var table_type_2 = $('#table-type-2');
var tr_item_type_1 = $(table_type_1).find('.tr-item');
var tr_item_type_2 = $(table_type_2).find('.tr-item');
$(table_type_1).find('.tr-item').remove();
$(table_type_2).find('.tr-item').remove();

function loadClassifyTable() {
    $('#table-type-1').remove();
    $('#table-type-2').remove();
    var count = $('.classify-group').length;

    if (count === 1) {
        //append table
        var container = $('#div-classify-list');
        $(table_type_1).clone().appendTo(container);

        //
        var root = $('.div-classify-group')[0];
        //change name of th 
        var classifyName = $(root).find('.classify-group-name').find('input').val();
        if (classifyName === '') {
            classifyName = 'Classify Name';
        }
        var thName = $('#table-type-1').find('th')[0];
        $(thName).text(classifyName);
        //change name of each row
        var itemList = $(root).find('.classify-item').find('input');
        for (var i = 0; i < itemList.length; i++) {
            var itemName = $(itemList[i]).val();
            if (itemName === '') {
                itemName = 'Item Name';
            }
            var temp = tr_item_type_1.clone();
            $(temp.find('.td-name')).text(itemName);
            temp.appendTo($('#table-type-1').find('.table-classify'));
        }
    } else if (count === 2) {
        //append table
        var container = $('#div-classify-list');
        $(table_type_2).clone().appendTo(container);
        //
        var root1 = $('.div-classify-group')[0];
        var root2 = $('.div-classify-group')[1];
        //change name of th 1
        var classifyName = $(root1).find('.classify-group-name').find('input').val();
        if (classifyName === '') {
            classifyName = 'Classify Name';
        }
        var thName = $('#table-type-2').find('th')[0];
        $(thName).text(classifyName);
        //change name of th 2
        var classifyName = $(root2).find('.classify-group-name').find('input').val();
        if (classifyName === '') {
            classifyName = 'Classify Name';
        }
        thName = $('#table-type-2').find('th')[1];
        $(thName).text(classifyName);
        //change name of each row
        var itemList1 = $(root1).find('.classify-item').find('input');
        var itemList2 = $(root2).find('.classify-item').find('input');
        for (var i = 0; i < itemList1.length; i++) {
            var itemName1 = $(itemList1[i]).val();
            if (itemName1 === '') {
                itemName1 = 'Item Name';
            }
            var temp = tr_item_type_2.clone();
            $(temp.find('.td-name')[0]).text(itemName1);
            $(temp.find('.td-name')[0]).attr('rowspan', itemList2.length);
            temp.appendTo($('#table-type-2').find('.table-classify'));
            for (var j = 0; j < itemList2.length; j++) {
                var itemName2 = $(itemList2[j]).val();
                if (itemName2 === '') {
                    itemName2 = 'Item Name';
                }
                if (j === 0) {
                    $(temp.find('.td-name')[1]).text(itemName2);
                } else {
                    var temp2 = tr_item_type_2.clone();
                    temp2.find('.td-name')[0].remove();
                    $(temp2.find('.td-name')[0]).text(itemName2);
                    temp2.appendTo($('#table-type-2').find('.table-classify'));
                }
            }
        }
    }
}

$(document).ready(function () {
    setBrandSelect();
    $('.classify-group').remove();
    $('#div-add-classify-group').remove();
    loadClassifyTable();
    loadClassifyImg();
    loadDisplayImg($('.img-display'));
});


