DELIMITER $$
create trigger generate_confirmation_num
before insert on wmk_order_summary for each row
begin
	declare prod_code_gen varchar(31);
    
    set prod_code_gen = lpad(right(cast(conv(substr(md5(concat(now(), NEW.cust_id)), 1, 16), 16, 10) as char), 16), 16, '0');
    
    set NEW.confirm_num = concat(date_format(now(), '%Y%m%d') , prod_code_gen);
    
end$$