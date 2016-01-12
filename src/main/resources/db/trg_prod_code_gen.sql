DELIMITER $$
create trigger generate_prod_code 
before insert on wmk_vendor_product for each row
begin
	declare prod_line_typ varchar(255);
    declare prod_label_typ varchar(255);
    declare v_user_id varchar(255);
    declare prod_code_gen varchar(31);
    
    set v_user_id = (select login_id from wmk_user_master where user_id = NEW.vend_id);
    
    set prod_line_typ = concat((select left(prod_typ, 2) from wmk_product_lines where prod_typ_id = NEW.prod_typ_id) , (select right(prod_typ,2) from wmk_product_lines where prod_typ_id = NEW.prod_typ_id));
    set prod_label_typ = concat((select left(NEW.prod_label, 2)) , (select right(NEW.prod_label, 2)));
    
    set prod_line_typ = (select upper(rpad(prod_line_typ, 4, 'X')));
    set prod_label_typ = (select upper(rpad(prod_label_typ, 4, 'X')));
    
    set prod_code_gen = lpad(right(cast(conv(substr(md5(concat(prod_line_typ, v_user_id, NEW.prod_label)), 1, 16), 16, 10) as char), 12), 12, '0');
    
    -- prod_line_typ [4] + prod_label [4] + numeric code [12]. total 20 chars
    set NEW.prod_code = concat(prod_line_typ , prod_label_typ , prod_code_gen);
    
end$$