INSERT INTO public.addresses (address_id, address) VALUES (1, 'B. St. Delavrancea 31/20 Baia Mare');
INSERT INTO public.contactdetails (contact_id, email, phonenumber) VALUES (1, 'popvladaurel@gmail.com', '0749589537');
INSERT INTO public.persons (cnp, name, address_address_id, contactdetails_contact_id) VALUES ('1840822245033', 'Pop Vlad-Aurel', 1, 1);
INSERT INTO public.useraccounts (account_id, passwordhash, person_cnp) VALUES ('vlad', 'sha1:64000:18:fw3A0wXNExI2jldh+FhrNcLccc0MJ1IG:iqVzuuQoa6V6+4y9QSU5zEAp', '1840822245033');