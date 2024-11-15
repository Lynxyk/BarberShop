PGDMP     
                    {         
   BarberShop    15.2    15.2 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16863 
   BarberShop    DATABASE     �   CREATE DATABASE "BarberShop" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';
    DROP DATABASE "BarberShop";
                postgres    false                        2615    17083    rls    SCHEMA        CREATE SCHEMA rls;
    DROP SCHEMA rls;
                postgres    false                        3079    17039    pg_stat_statements 	   EXTENSION     F   CREATE EXTENSION IF NOT EXISTS pg_stat_statements WITH SCHEMA public;
 #   DROP EXTENSION pg_stat_statements;
                   false            �           0    0    EXTENSION pg_stat_statements    COMMENT     u   COMMENT ON EXTENSION pg_stat_statements IS 'track planning and execution statistics of all SQL statements executed';
                        false    2            �           1247    17181    address_postal_code    TYPE     r   CREATE TYPE public.address_postal_code AS (
	address character varying(50),
	postal_code character varying(10)
);
 &   DROP TYPE public.address_postal_code;
       public          postgres    false            �            1255    17082 "   calculate_employee_rating(integer)    FUNCTION     G  CREATE FUNCTION public.calculate_employee_rating(master_id integer) RETURNS numeric
    LANGUAGE plpgsql
    AS $$ 
DECLARE  
  new_rating NUMERIC(3,2);  
BEGIN  
  SELECT AVG(master_rating) INTO new_rating  
  FROM Booking  
  WHERE Booking.master_id = calculate_employee_rating.master_id;  
 
  RETURN new_rating; 
END; 
$$;
 C   DROP FUNCTION public.calculate_employee_rating(master_id integer);
       public          postgres    false            �           0    0 5   FUNCTION calculate_employee_rating(master_id integer)    ACL     h   GRANT ALL ON FUNCTION public.calculate_employee_rating(master_id integer) TO "Staff" WITH GRANT OPTION;
          public          postgres    false    249            �            1255    17153    deletebookingbyid(integer) 	   PROCEDURE     �   CREATE PROCEDURE public.deletebookingbyid(IN in_id integer)
    LANGUAGE plpgsql
    AS $$ 
BEGIN 
    DELETE FROM Booking WHERE id = in_id; 
END; 
$$;
 ;   DROP PROCEDURE public.deletebookingbyid(IN in_id integer);
       public          postgres    false            �            1255    17151    deleteclientbonusbyid(integer) 	   PROCEDURE     �   CREATE PROCEDURE public.deleteclientbonusbyid(IN in_id integer)
    LANGUAGE plpgsql
    AS $$ 
BEGIN 
    DELETE FROM Client_Bonus WHERE id = in_id; 
END; 
$$;
 ?   DROP PROCEDURE public.deleteclientbonusbyid(IN in_id integer);
       public          postgres    false            �            1255    17150    deleteclientbyid(integer) 	   PROCEDURE     �   CREATE PROCEDURE public.deleteclientbyid(IN in_id integer)
    LANGUAGE plpgsql
    AS $$ 
BEGIN 
    DELETE FROM Client WHERE id = in_id; 
END; 
$$;
 :   DROP PROCEDURE public.deleteclientbyid(IN in_id integer);
       public          postgres    false            �            1255    17148    deleteemployeebyid(integer) 	   PROCEDURE     �   CREATE PROCEDURE public.deleteemployeebyid(IN in_id integer)
    LANGUAGE plpgsql
    AS $$ 
BEGIN 
    DELETE FROM Employee WHERE id = in_id; 
END; 
$$;
 <   DROP PROCEDURE public.deleteemployeebyid(IN in_id integer);
       public          postgres    false            �            1255    17147     deleteestablishmentbyid(integer) 	   PROCEDURE     �   CREATE PROCEDURE public.deleteestablishmentbyid(IN in_id integer)
    LANGUAGE plpgsql
    AS $$ 
BEGIN 
    DELETE FROM Establishment WHERE id = in_id; 
END; 
$$;
 A   DROP PROCEDURE public.deleteestablishmentbyid(IN in_id integer);
       public          postgres    false            �            1255    17156 0   deleteestablishmentemployeebyemployeeid(integer) 	   PROCEDURE     �   CREATE PROCEDURE public.deleteestablishmentemployeebyemployeeid(IN in_employee_id integer)
    LANGUAGE plpgsql
    AS $$ 
BEGIN 
    DELETE FROM Establishment_Employee WHERE employee_id = in_employee_id; 
END; 
$$;
 Z   DROP PROCEDURE public.deleteestablishmentemployeebyemployeeid(IN in_employee_id integer);
       public          postgres    false            �            1255    17154    deletereceiptbyid(integer) 	   PROCEDURE     �   CREATE PROCEDURE public.deletereceiptbyid(IN in_id integer)
    LANGUAGE plpgsql
    AS $$ 
BEGIN 
    DELETE FROM Receipt WHERE id = in_id; 
END; 
$$;
 ;   DROP PROCEDURE public.deletereceiptbyid(IN in_id integer);
       public          postgres    false            �            1255    17152    deleteservicebyid(integer) 	   PROCEDURE     �   CREATE PROCEDURE public.deleteservicebyid(IN in_id integer)
    LANGUAGE plpgsql
    AS $$ 
BEGIN 
    DELETE FROM Service WHERE id = in_id; 
END; 
$$;
 ;   DROP PROCEDURE public.deleteservicebyid(IN in_id integer);
       public          postgres    false            �            1255    17155    deleteuserbyid(integer) 	   PROCEDURE     �   CREATE PROCEDURE public.deleteuserbyid(IN in_id integer)
    LANGUAGE plpgsql
    AS $$ 
BEGIN 
    DELETE FROM Users WHERE id = in_id; 
END; 
$$;
 8   DROP PROCEDURE public.deleteuserbyid(IN in_id integer);
       public          postgres    false                       1255    16864    update_client_bonus()    FUNCTION     t  CREATE FUNCTION public.update_client_bonus() RETURNS trigger
    LANGUAGE plpgsql
    AS $$ 
DECLARE 
    receipt_total NUMERIC(10,2); 
    bonus_amount INTEGER; 
    current_bonus INTEGER; 
BEGIN 
    SELECT price INTO receipt_total FROM Receipt WHERE id = NEW.booking_id; 
    bonus_amount := CAST(receipt_total * 0.05 AS INTEGER); 
   
    SELECT bonus INTO current_bonus FROM Client_Bonus WHERE client_id = NEW.client_id; 
    
    IF NOT FOUND THEN 
        current_bonus := 0; 
    END IF; 
    
    UPDATE Client_Bonus SET bonus = current_bonus + bonus_amount WHERE client_id = NEW.client_id; 
    RETURN NEW; 
END; 
$$;
 ,   DROP FUNCTION public.update_client_bonus();
       public          postgres    false            �            1255    16865    update_client_status()    FUNCTION     �  CREATE FUNCTION public.update_client_status() RETURNS trigger
    LANGUAGE plpgsql
    AS $$ 
BEGIN 
    UPDATE Client SET client_status =  
        CASE  
            WHEN (SELECT COUNT(*) FROM Booking WHERE client_id = NEW.client_id) >= 20 THEN 'VIP' 
            WHEN (SELECT COUNT(*) FROM Booking WHERE client_id = NEW.client_id) >= 3 THEN 'Premium' 
            ELSE 'Regular' 
        END 
    WHERE id = NEW.client_id; 
    RETURN NEW; 
END; 
$$;
 -   DROP FUNCTION public.update_client_status();
       public          postgres    false            �           0    0    FUNCTION update_client_status()    ACL     R   GRANT ALL ON FUNCTION public.update_client_status() TO "Staff" WITH GRANT OPTION;
          public          postgres    false    252            �            1255    17080    update_employee_rating()    FUNCTION     �   CREATE FUNCTION public.update_employee_rating() RETURNS trigger
    LANGUAGE plpgsql
    AS $$ 
BEGIN  
  UPDATE Employee  
  SET rating = calculate_employee_rating(NEW.master_id) 
  WHERE id = NEW.master_id;  
 
  RETURN NEW;  
END; 
$$;
 /   DROP FUNCTION public.update_employee_rating();
       public          postgres    false            �           0    0 !   FUNCTION update_employee_rating()    ACL     T   GRANT ALL ON FUNCTION public.update_employee_rating() TO "Staff" WITH GRANT OPTION;
          public          postgres    false    250            �            1259    16882    employee    TABLE     k  CREATE TABLE public.employee (
    id integer NOT NULL,
    full_name character varying(255),
    "position" character varying(255),
    contact_information character varying(255),
    experience integer,
    salary numeric(10,2),
    brief_information text,
    age integer,
    rating numeric(3,2)
);

ALTER TABLE ONLY public.employee FORCE ROW LEVEL SECURITY;
    DROP TABLE public.employee;
       public         heap    postgres    false            �           0    0    TABLE employee    ACL     �   GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.employee TO "Supervisor" WITH GRANT OPTION;
GRANT SELECT ON TABLE public.employee TO "Analyst" WITH GRANT OPTION;
GRANT SELECT,INSERT,TRIGGER,UPDATE ON TABLE public.employee TO "Staff" WITH GRANT OPTION;
          public          postgres    false    222                       1255    17118 *   employee_access_predicate(public.employee)    FUNCTION     �  CREATE FUNCTION rls.employee_access_predicate(employee public.employee) RETURNS boolean
    LANGUAGE plpgsql
    AS $$ 
BEGIN 
  IF current_user IN (SELECT u.usename 
                         FROM pg_user u 
                         JOIN pg_group g ON u.usesysid = ANY(g.grolist) 
                         WHERE g.groname IN ('Manager', 'Staff')) THEN 
     
    RETURN TRUE; 
  ELSIF current_user IN (SELECT u.usename 
                         FROM pg_user u 
                         JOIN pg_group g ON u.usesysid = ANY(g.grolist) 
                         WHERE g.groname = 'Analyst') THEN 
    RETURN TRUE; 
  ELSE 
    RETURN FALSE;
  END IF; 
END; 
$$;
 G   DROP FUNCTION rls.employee_access_predicate(employee public.employee);
       rls          postgres    false    7    222            �            1259    17157    bestemployee    VIEW     �   CREATE VIEW public.bestemployee AS
 SELECT employee.full_name,
    employee.rating
   FROM public.employee
  ORDER BY employee.rating DESC
 LIMIT 5;
    DROP VIEW public.bestemployee;
       public          postgres    false    222    222            �           0    0    TABLE bestemployee    ACL     8   GRANT SELECT ON TABLE public.bestemployee TO "Analyst";
          public          postgres    false    235            �            1259    16867    booking    TABLE     �   CREATE TABLE public.booking (
    id integer NOT NULL,
    client_id integer,
    service_id integer,
    booking_date date,
    master_rating numeric(3,2) DEFAULT 0.0,
    master_id integer
);
    DROP TABLE public.booking;
       public         heap    postgres    false            �           0    0    TABLE booking    ACL     2  GRANT SELECT ON TABLE public.booking TO "Analyst" WITH GRANT OPTION;
GRANT SELECT,INSERT,TRIGGER ON TABLE public.booking TO "Staff" WITH GRANT OPTION;
GRANT SELECT,INSERT,DELETE ON TABLE public.booking TO "Manager" WITH GRANT OPTION;
GRANT SELECT ON TABLE public.booking TO "Supervisor" WITH GRANT OPTION;
          public          postgres    false    216            �            1259    16871    booking_id_seq    SEQUENCE     �   CREATE SEQUENCE public.booking_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.booking_id_seq;
       public          postgres    false    216            �           0    0    booking_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.booking_id_seq OWNED BY public.booking.id;
          public          postgres    false    217            �           0    0    SEQUENCE booking_id_seq    ACL     �   GRANT ALL ON SEQUENCE public.booking_id_seq TO "Manager";
GRANT ALL ON SEQUENCE public.booking_id_seq TO "Staff";
GRANT ALL ON SEQUENCE public.booking_id_seq TO "Analyst";
GRANT ALL ON SEQUENCE public.booking_id_seq TO "Supervisor";
          public          postgres    false    217            �            1259    16872    client    TABLE     �   CREATE TABLE public.client (
    id integer NOT NULL,
    full_name character varying(255),
    contact_information character varying(255),
    client_status character varying(255)
);
    DROP TABLE public.client;
       public         heap    postgres    false            �           0    0    TABLE client    ACL     �  GRANT SELECT ON TABLE public.client TO "Analyst" WITH GRANT OPTION;
GRANT TRIGGER ON TABLE public.client TO "Staff";
GRANT SELECT,INSERT,UPDATE ON TABLE public.client TO "Staff" WITH GRANT OPTION;
GRANT TRIGGER,UPDATE ON TABLE public.client TO "Manager";
GRANT SELECT,INSERT,DELETE ON TABLE public.client TO "Manager" WITH GRANT OPTION;
GRANT SELECT ON TABLE public.client TO "Supervisor" WITH GRANT OPTION;
          public          postgres    false    218            �            1259    16877    client_bonus    TABLE     h   CREATE TABLE public.client_bonus (
    id integer NOT NULL,
    client_id integer,
    bonus integer
);
     DROP TABLE public.client_bonus;
       public         heap    postgres    false            �           0    0    TABLE client_bonus    ACL     )  GRANT SELECT ON TABLE public.client_bonus TO "Analyst" WITH GRANT OPTION;
GRANT SELECT ON TABLE public.client_bonus TO "Staff" WITH GRANT OPTION;
GRANT SELECT ON TABLE public.client_bonus TO "Manager" WITH GRANT OPTION;
GRANT SELECT ON TABLE public.client_bonus TO "Supervisor" WITH GRANT OPTION;
          public          postgres    false    219            �            1259    16880    client_bonus_id_seq    SEQUENCE     �   CREATE SEQUENCE public.client_bonus_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.client_bonus_id_seq;
       public          postgres    false    219            �           0    0    client_bonus_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.client_bonus_id_seq OWNED BY public.client_bonus.id;
          public          postgres    false    220            �           0    0    SEQUENCE client_bonus_id_seq    ACL     �   GRANT ALL ON SEQUENCE public.client_bonus_id_seq TO "Manager";
GRANT ALL ON SEQUENCE public.client_bonus_id_seq TO "Staff";
GRANT ALL ON SEQUENCE public.client_bonus_id_seq TO "Analyst";
GRANT ALL ON SEQUENCE public.client_bonus_id_seq TO "Supervisor";
          public          postgres    false    220            �            1259    16881    client_id_seq    SEQUENCE     �   CREATE SEQUENCE public.client_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.client_id_seq;
       public          postgres    false    218            �           0    0    client_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.client_id_seq OWNED BY public.client.id;
          public          postgres    false    221            �           0    0    SEQUENCE client_id_seq    ACL     �   GRANT ALL ON SEQUENCE public.client_id_seq TO "Manager";
GRANT ALL ON SEQUENCE public.client_id_seq TO "Staff";
GRANT ALL ON SEQUENCE public.client_id_seq TO "Analyst";
GRANT ALL ON SEQUENCE public.client_id_seq TO "Supervisor";
          public          postgres    false    221            �            1259    16887    employee_id_seq    SEQUENCE     �   CREATE SEQUENCE public.employee_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.employee_id_seq;
       public          postgres    false    222            �           0    0    employee_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.employee_id_seq OWNED BY public.employee.id;
          public          postgres    false    223            �           0    0    SEQUENCE employee_id_seq    ACL     �   GRANT ALL ON SEQUENCE public.employee_id_seq TO "Manager";
GRANT ALL ON SEQUENCE public.employee_id_seq TO "Staff";
GRANT ALL ON SEQUENCE public.employee_id_seq TO "Analyst";
GRANT ALL ON SEQUENCE public.employee_id_seq TO "Supervisor";
          public          postgres    false    223            �            1259    16888    establishment    TABLE     �   CREATE TABLE public.establishment (
    id integer NOT NULL,
    establishment_type character varying(255),
    phone_number character varying(20),
    number_of_employees integer,
    address_with_postal_code public.address_postal_code
);
 !   DROP TABLE public.establishment;
       public         heap    postgres    false    928            �           0    0    TABLE establishment    ACL     �   GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.establishment TO "Supervisor" WITH GRANT OPTION;
GRANT SELECT ON TABLE public.establishment TO "Analyst" WITH GRANT OPTION;
          public          postgres    false    224            �            1259    16893    establishment_employee    TABLE     x   CREATE TABLE public.establishment_employee (
    establishment_id integer NOT NULL,
    employee_id integer NOT NULL
);
 *   DROP TABLE public.establishment_employee;
       public         heap    postgres    false            �           0    0    TABLE establishment_employee    ACL     �   GRANT SELECT ON TABLE public.establishment_employee TO "Supervisor";
GRANT SELECT ON TABLE public.establishment_employee TO "Analyst" WITH GRANT OPTION;
GRANT SELECT ON TABLE public.establishment_employee TO "Staff" WITH GRANT OPTION;
          public          postgres    false    225            �            1259    16896    establishment_id_seq    SEQUENCE     �   CREATE SEQUENCE public.establishment_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.establishment_id_seq;
       public          postgres    false    224            �           0    0    establishment_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.establishment_id_seq OWNED BY public.establishment.id;
          public          postgres    false    226            �           0    0    SEQUENCE establishment_id_seq    ACL       GRANT ALL ON SEQUENCE public.establishment_id_seq TO "Manager";
GRANT ALL ON SEQUENCE public.establishment_id_seq TO "Staff";
GRANT ALL ON SEQUENCE public.establishment_id_seq TO "Analyst";
GRANT ALL ON SEQUENCE public.establishment_id_seq TO "Supervisor";
          public          postgres    false    226            �            1259    16897    receipt    TABLE     �   CREATE TABLE public.receipt (
    id integer NOT NULL,
    client_id integer,
    establishment_id integer,
    booking_id integer,
    price numeric(10,2)
);
    DROP TABLE public.receipt;
       public         heap    postgres    false            �           0    0    TABLE receipt    ACL     �   GRANT SELECT ON TABLE public.receipt TO "Analyst" WITH GRANT OPTION;
GRANT SELECT ON TABLE public.receipt TO "Supervisor" WITH GRANT OPTION;
          public          postgres    false    227            �            1259    17161 
   ind_profit    VIEW     �   CREATE VIEW public.ind_profit AS
 SELECT receipt.establishment_id,
    sum(receipt.price) AS "Заработок"
   FROM public.receipt
  GROUP BY receipt.establishment_id
  ORDER BY receipt.establishment_id;
    DROP VIEW public.ind_profit;
       public          postgres    false    227    227            �           0    0    TABLE ind_profit    ACL     6   GRANT SELECT ON TABLE public.ind_profit TO "Analyst";
          public          postgres    false    236            �            1259    16901    service    TABLE     s   CREATE TABLE public.service (
    id integer NOT NULL,
    name character varying(255),
    price numeric(10,2)
);
    DROP TABLE public.service;
       public         heap    postgres    false            �           0    0    TABLE service    ACL     �   GRANT SELECT ON TABLE public.service TO "Analyst" WITH GRANT OPTION;
GRANT SELECT ON TABLE public.service TO "Supervisor" WITH GRANT OPTION;
          public          postgres    false    229            �            1259    17165    notopservice    VIEW     l  CREATE VIEW public.notopservice AS
 SELECT service.name,
    COALESCE(sum(receipt.price), (0)::numeric) AS total_profit
   FROM ((public.service
     LEFT JOIN public.booking ON ((service.id = booking.service_id)))
     LEFT JOIN public.receipt ON ((booking.id = receipt.booking_id)))
  GROUP BY service.name
  ORDER BY COALESCE(sum(receipt.price), (0)::numeric);
    DROP VIEW public.notopservice;
       public          postgres    false    227    216    216    227    229    229            �           0    0    TABLE notopservice    ACL     8   GRANT SELECT ON TABLE public.notopservice TO "Analyst";
          public          postgres    false    237            �            1259    16900    receipt_id_seq    SEQUENCE     �   CREATE SEQUENCE public.receipt_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.receipt_id_seq;
       public          postgres    false    227            �           0    0    receipt_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.receipt_id_seq OWNED BY public.receipt.id;
          public          postgres    false    228            �           0    0    SEQUENCE receipt_id_seq    ACL     �   GRANT ALL ON SEQUENCE public.receipt_id_seq TO "Manager";
GRANT ALL ON SEQUENCE public.receipt_id_seq TO "Staff";
GRANT ALL ON SEQUENCE public.receipt_id_seq TO "Analyst";
GRANT ALL ON SEQUENCE public.receipt_id_seq TO "Supervisor";
          public          postgres    false    228            �            1259    16904    service_id_seq    SEQUENCE     �   CREATE SEQUENCE public.service_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.service_id_seq;
       public          postgres    false    229            �           0    0    service_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.service_id_seq OWNED BY public.service.id;
          public          postgres    false    230            �           0    0    SEQUENCE service_id_seq    ACL     �   GRANT ALL ON SEQUENCE public.service_id_seq TO "Manager";
GRANT ALL ON SEQUENCE public.service_id_seq TO "Staff";
GRANT ALL ON SEQUENCE public.service_id_seq TO "Analyst";
GRANT ALL ON SEQUENCE public.service_id_seq TO "Supervisor";
          public          postgres    false    230            �            1259    17170 
   topservice    VIEW     7  CREATE VIEW public.topservice AS
 SELECT service.name,
    sum(receipt.price) AS total_profit
   FROM ((public.receipt
     JOIN public.booking ON ((receipt.booking_id = booking.id)))
     JOIN public.service ON ((booking.service_id = service.id)))
  GROUP BY service.name
  ORDER BY (sum(receipt.price)) DESC;
    DROP VIEW public.topservice;
       public          postgres    false    216    229    229    227    227    216            �           0    0    TABLE topservice    ACL     6   GRANT SELECT ON TABLE public.topservice TO "Analyst";
          public          postgres    false    238            �            1259    17175    totalprofit    VIEW     t   CREATE VIEW public.totalprofit AS
 SELECT sum(receipt.price) AS "Общая прибыль"
   FROM public.receipt;
    DROP VIEW public.totalprofit;
       public          postgres    false    227            �           0    0    TABLE totalprofit    ACL     7   GRANT SELECT ON TABLE public.totalprofit TO "Analyst";
          public          postgres    false    239            �            1259    16905    users    TABLE     �   CREATE TABLE public.users (
    id integer NOT NULL,
    username character varying(255),
    password character varying(255),
    user_group character varying(255),
    employee_id integer
);
    DROP TABLE public.users;
       public         heap    postgres    false            �           0    0    TABLE users    ACL     �   GRANT SELECT ON TABLE public.users TO "Supervisor";
GRANT SELECT ON TABLE public.users TO "Analyst" WITH GRANT OPTION;
GRANT SELECT ON TABLE public.users TO "Staff";
          public          postgres    false    231            �            1259    16910    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    231            �           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    232            �           0    0    SEQUENCE users_id_seq    ACL     �   GRANT ALL ON SEQUENCE public.users_id_seq TO "Staff";
GRANT ALL ON SEQUENCE public.users_id_seq TO "Analyst";
GRANT ALL ON SEQUENCE public.users_id_seq TO "Supervisor";
          public          postgres    false    232            �           2604    16911 
   booking id    DEFAULT     h   ALTER TABLE ONLY public.booking ALTER COLUMN id SET DEFAULT nextval('public.booking_id_seq'::regclass);
 9   ALTER TABLE public.booking ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    216            �           2604    16912 	   client id    DEFAULT     f   ALTER TABLE ONLY public.client ALTER COLUMN id SET DEFAULT nextval('public.client_id_seq'::regclass);
 8   ALTER TABLE public.client ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    218            �           2604    16913    client_bonus id    DEFAULT     r   ALTER TABLE ONLY public.client_bonus ALTER COLUMN id SET DEFAULT nextval('public.client_bonus_id_seq'::regclass);
 >   ALTER TABLE public.client_bonus ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    219            �           2604    16914    employee id    DEFAULT     j   ALTER TABLE ONLY public.employee ALTER COLUMN id SET DEFAULT nextval('public.employee_id_seq'::regclass);
 :   ALTER TABLE public.employee ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    223    222            �           2604    16915    establishment id    DEFAULT     t   ALTER TABLE ONLY public.establishment ALTER COLUMN id SET DEFAULT nextval('public.establishment_id_seq'::regclass);
 ?   ALTER TABLE public.establishment ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    226    224            �           2604    16916 
   receipt id    DEFAULT     h   ALTER TABLE ONLY public.receipt ALTER COLUMN id SET DEFAULT nextval('public.receipt_id_seq'::regclass);
 9   ALTER TABLE public.receipt ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    228    227            �           2604    16917 
   service id    DEFAULT     h   ALTER TABLE ONLY public.service ALTER COLUMN id SET DEFAULT nextval('public.service_id_seq'::regclass);
 9   ALTER TABLE public.service ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    230    229            �           2604    16918    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    232    231            �          0    16867    booking 
   TABLE DATA           d   COPY public.booking (id, client_id, service_id, booking_date, master_rating, master_id) FROM stdin;
    public          postgres    false    216   ��       �          0    16872    client 
   TABLE DATA           S   COPY public.client (id, full_name, contact_information, client_status) FROM stdin;
    public          postgres    false    218   �       �          0    16877    client_bonus 
   TABLE DATA           <   COPY public.client_bonus (id, client_id, bonus) FROM stdin;
    public          postgres    false    219   ��       �          0    16882    employee 
   TABLE DATA           �   COPY public.employee (id, full_name, "position", contact_information, experience, salary, brief_information, age, rating) FROM stdin;
    public          postgres    false    222   ��       �          0    16888    establishment 
   TABLE DATA           |   COPY public.establishment (id, establishment_type, phone_number, number_of_employees, address_with_postal_code) FROM stdin;
    public          postgres    false    224   ��       �          0    16893    establishment_employee 
   TABLE DATA           O   COPY public.establishment_employee (establishment_id, employee_id) FROM stdin;
    public          postgres    false    225   u�       �          0    16897    receipt 
   TABLE DATA           U   COPY public.receipt (id, client_id, establishment_id, booking_id, price) FROM stdin;
    public          postgres    false    227   ��       �          0    16901    service 
   TABLE DATA           2   COPY public.service (id, name, price) FROM stdin;
    public          postgres    false    229   �       �          0    16905    users 
   TABLE DATA           P   COPY public.users (id, username, password, user_group, employee_id) FROM stdin;
    public          postgres    false    231   ��       �           0    0    booking_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.booking_id_seq', 10, true);
          public          postgres    false    217            �           0    0    client_bonus_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.client_bonus_id_seq', 505, true);
          public          postgres    false    220            �           0    0    client_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.client_id_seq', 504, true);
          public          postgres    false    221            �           0    0    employee_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.employee_id_seq', 24, true);
          public          postgres    false    223            �           0    0    establishment_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.establishment_id_seq', 4, true);
          public          postgres    false    226            �           0    0    receipt_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.receipt_id_seq', 6, true);
          public          postgres    false    228            �           0    0    service_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.service_id_seq', 6, true);
          public          postgres    false    230            �           0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 27, true);
          public          postgres    false    232            �           2606    16920    booking booking_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.booking
    ADD CONSTRAINT booking_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.booking DROP CONSTRAINT booking_pkey;
       public            postgres    false    216            �           2606    16922    client_bonus client_bonus_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.client_bonus
    ADD CONSTRAINT client_bonus_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.client_bonus DROP CONSTRAINT client_bonus_pkey;
       public            postgres    false    219            �           2606    16924    client client_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.client DROP CONSTRAINT client_pkey;
       public            postgres    false    218            �           2606    16926    employee employee_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.employee DROP CONSTRAINT employee_pkey;
       public            postgres    false    222            �           2606    16928 2   establishment_employee establishment_employee_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.establishment_employee
    ADD CONSTRAINT establishment_employee_pkey PRIMARY KEY (establishment_id, employee_id);
 \   ALTER TABLE ONLY public.establishment_employee DROP CONSTRAINT establishment_employee_pkey;
       public            postgres    false    225    225            �           2606    16930     establishment establishment_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.establishment
    ADD CONSTRAINT establishment_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.establishment DROP CONSTRAINT establishment_pkey;
       public            postgres    false    224            �           2606    16932    receipt receipt_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.receipt
    ADD CONSTRAINT receipt_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.receipt DROP CONSTRAINT receipt_pkey;
       public            postgres    false    227            �           2606    16934    service service_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.service
    ADD CONSTRAINT service_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.service DROP CONSTRAINT service_pkey;
       public            postgres    false    229            �           2606    16936    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    231            �           1259    17146    idx_contact_information    INDEX     Y   CREATE INDEX idx_contact_information ON public.client USING btree (contact_information);
 +   DROP INDEX public.idx_contact_information;
       public            postgres    false    218            �           2620    17081    booking booking_rating_insert    TRIGGER     �   CREATE TRIGGER booking_rating_insert AFTER INSERT ON public.booking FOR EACH ROW EXECUTE FUNCTION public.update_employee_rating();
 6   DROP TRIGGER booking_rating_insert ON public.booking;
       public          postgres    false    216    250            �           2620    16938 #   receipt update_client_bonus_trigger    TRIGGER     �   CREATE TRIGGER update_client_bonus_trigger AFTER INSERT ON public.receipt FOR EACH ROW EXECUTE FUNCTION public.update_client_bonus();
 <   DROP TRIGGER update_client_bonus_trigger ON public.receipt;
       public          postgres    false    227    267            �           2620    16939 $   booking update_client_status_trigger    TRIGGER     �   CREATE TRIGGER update_client_status_trigger AFTER INSERT ON public.booking FOR EACH ROW EXECUTE FUNCTION public.update_client_status();
 =   DROP TRIGGER update_client_status_trigger ON public.booking;
       public          postgres    false    252    216            �           2606    16940    booking booking_client_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.booking
    ADD CONSTRAINT booking_client_id_fkey FOREIGN KEY (client_id) REFERENCES public.client(id);
 H   ALTER TABLE ONLY public.booking DROP CONSTRAINT booking_client_id_fkey;
       public          postgres    false    216    3275    218            �           2606    16945 (   client_bonus client_bonus_client_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.client_bonus
    ADD CONSTRAINT client_bonus_client_id_fkey FOREIGN KEY (client_id) REFERENCES public.client(id);
 R   ALTER TABLE ONLY public.client_bonus DROP CONSTRAINT client_bonus_client_id_fkey;
       public          postgres    false    3275    219    218            �           2606    16950 >   establishment_employee establishment_employee_employee_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.establishment_employee
    ADD CONSTRAINT establishment_employee_employee_id_fkey FOREIGN KEY (employee_id) REFERENCES public.employee(id);
 h   ALTER TABLE ONLY public.establishment_employee DROP CONSTRAINT establishment_employee_employee_id_fkey;
       public          postgres    false    3280    225    222            �           2606    16955 C   establishment_employee establishment_employee_establishment_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.establishment_employee
    ADD CONSTRAINT establishment_employee_establishment_id_fkey FOREIGN KEY (establishment_id) REFERENCES public.establishment(id);
 m   ALTER TABLE ONLY public.establishment_employee DROP CONSTRAINT establishment_employee_establishment_id_fkey;
       public          postgres    false    224    3282    225            �           2606    16960    receipt receipt_booking_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.receipt
    ADD CONSTRAINT receipt_booking_id_fkey FOREIGN KEY (booking_id) REFERENCES public.booking(id);
 I   ALTER TABLE ONLY public.receipt DROP CONSTRAINT receipt_booking_id_fkey;
       public          postgres    false    3273    227    216            �           2606    16965    receipt receipt_client_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.receipt
    ADD CONSTRAINT receipt_client_id_fkey FOREIGN KEY (client_id) REFERENCES public.client(id);
 H   ALTER TABLE ONLY public.receipt DROP CONSTRAINT receipt_client_id_fkey;
       public          postgres    false    227    218    3275            �           2606    16970 %   receipt receipt_establishment_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.receipt
    ADD CONSTRAINT receipt_establishment_id_fkey FOREIGN KEY (establishment_id) REFERENCES public.establishment(id);
 O   ALTER TABLE ONLY public.receipt DROP CONSTRAINT receipt_establishment_id_fkey;
       public          postgres    false    224    3282    227            �           2606    16975    booking service_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.booking
    ADD CONSTRAINT service_id FOREIGN KEY (service_id) REFERENCES public.service(id) NOT VALID;
 <   ALTER TABLE ONLY public.booking DROP CONSTRAINT service_id;
       public          postgres    false    216    3288    229            �           2606    16980    users users_employee_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_employee_id_fkey FOREIGN KEY (employee_id) REFERENCES public.employee(id);
 F   ALTER TABLE ONLY public.users DROP CONSTRAINT users_employee_id_fkey;
       public          postgres    false    222    3280    231            �           3256    17137    employee edit_employee    POLICY       CREATE POLICY edit_employee ON public.employee USING (((id IN ( SELECT establishment_employee.employee_id
   FROM public.establishment_employee
  WHERE (establishment_employee.establishment_id = ( SELECT establishment_employee_1.establishment_id
           FROM (public.establishment_employee establishment_employee_1
             JOIN public.users USING (employee_id))
          WHERE (((users.user_group)::text = 'supervisor'::text) AND ((users.username)::text = CURRENT_USER)))))) OR rls.employee_access_predicate(employee.*)));
 .   DROP POLICY edit_employee ON public.employee;
       public          postgres    false    222    222    268    231    231    231    225    225            ~           3256    16986    establishment edit_salon    POLICY     ,  CREATE POLICY edit_salon ON public.establishment USING ((id = ( SELECT establishment_employee.establishment_id
   FROM (public.establishment_employee
     JOIN public.users USING (employee_id))
  WHERE (((users.user_group)::text = 'supervisor'::text) AND ((users.username)::text = CURRENT_USER)))));
 0   DROP POLICY edit_salon ON public.establishment;
       public          postgres    false    225    231    231    231    224    225    224            |           0    16882    employee    ROW SECURITY     6   ALTER TABLE public.employee ENABLE ROW LEVEL SECURITY;          public          postgres    false    222            }           0    16888    establishment    ROW SECURITY     ;   ALTER TABLE public.establishment ENABLE ROW LEVEL SECURITY;          public          postgres    false    224                       3256    16988 (   establishment look_establishment_analyst    POLICY     �   CREATE POLICY look_establishment_analyst ON public.establishment USING ((id IN ( SELECT establishment_employee.establishment_id
   FROM public.establishment_employee
  WHERE (CURRENT_USER = 'PetrovGlebPetrovich@gmail.com'::name))));
 @   DROP POLICY look_establishment_analyst ON public.establishment;
       public          postgres    false    224    225    224            �   Z   x�m��	 !D�s�e���l�ulĽ�����b���&5��q���(R�@QŤ]���w����h��^В��	�[�<ĸ�� >:Q#�      �      x��]ˎ�]��B� A��������*[/��&@������Á"'�a �n4�Dc��_�����=�6��� ��r�d�:�:�=tu����������y|�����������pw|�����O�������~1���,�n�_��Grux�?������4���g�������������g����_���C'<^��<~z���֏��#{u���N?}�p������Ǉ�^�0��ӻ���O��?�n�ן�?�qouV��s��q/f�D��~�z��?��wO���7���ǧ�üч��ƾ�W.��'�_^���t��38MGg���yb~����~�}{���ǑM8��.Xw�r�ˠ��~�p�Ӳ���!�Fٝ�лe��3����{|��P[�]�)\���M�p���C�z=��"Z� Vqr��X=��W3���+�(��I�8����u#���|� k_UƯ�w�s�]�����xN�D�������ᕟ���A��c��VK��J���z~������F�4?����0�Bn~�W��L��vF�v�	�y#��M�����3������G��{��\P7�W��䒜'���z���˗S��F�a?`�H��S������ћ�h!��'�@�N�$��$�d��*N�^�?��o��4�k�}�yN�>�a���
Ա%4�����fo����Vh�c���ْ�3��Đ�3���h���ΩP�3J���z�(�Z@	O(������͂�����/N�|�t&�����Dz���"�ng@��ֻ���z(K��.���H��Ժ������z^z�D�����Q�2�����\t��s�d(/��w�lA��ml�e�o�9^������>���9��g5���yS��l(#�?��#]������z�S��Y%��z������-\t�����(3Ou���	m�1xI$���ȩ������WY���F� �	!�Ss����~ �(s�S5���c����/'�X���0����x~�s�J�� QF~1a�u$'$��a�s��sO��8�l�0b��	���e;�������|h��et�T/gV{;?o��c l(w��3��P�]���b(�>���i�ym$(���j�H�W[��e⠹RPY��e�?�2�=�g�# B������dV#�@y�������.�u$(�j��\9��-q��������L:bO�Ng�����J�@��+���e�Ϣ��<;��G���O��Џ�:ۏ�l��Qx�݂����D�U��.�s��a��:�uO��'H�P������d��}�(�~����	��� Dy�?�y�T��ev�6�e�ӧ�~�$����ܭ���e��u��aBȻy����u��ܯ�S��ft��S��Ӹ�{�	�p�����w�����ό�,(�8Eel�6c�u�e�Upm�k�����H�gE`@���Ӫ���H,�m�<(ߪ�q��!��y�g �0��ɂ3�3�`�p+=E8xP�}������B����V`ȶ�gL8����.��ғ�3��`��������=M8�p�p�����U�!���Exꨔ�ߝ�8���{"���p���|��S��=�8t(�~5?��9|���L�38�P�s�s���o��wQ�:-�=e'd�����͊��åv,`FB�O���1�$�,�$����z�:x�I��)o���L��|����X�'���c���|��?��3X�B�o�y*�~&@�(5}������5�������޴���J�A�7Ghv1�@�7�O�"*�Rr��-H�)�p��[��#z��r����d��E��{=�\sdWX����oq�ŕ�W=��:+��+C뷸ԊH��˫.pq=�
��.h��v�|Ciܐ	�����XɈo��|!����p�lp�V��?j�F�y��H�(�����TIs��%��\T��nh�(�����F�9��Wn{P�6J�{������"����m�)��Rr���w�*��)�^�W��S�y:&�CElT�Ż�=E,� �Xm�(���E|���XwEA�a�ҷ�!�����4���m���V�ҷQ��Z*�FIx_l�OT(�1���C-�Uˮ�p�$ܤ�:��p���P7b��W�*���Yzu(���J�աn����W��QR�T_��F�B~u(��Y/�_*��R� �P�6JǍ
�C�Xi�`J���J֡n��+DX���Q2nPaj�Ǝ�2�C)�(-W�Up�wUB�C�(W)�up�S��P
7J��Z�C]�(-7��r�tܠ�:T�M�79��X�r��7��cj�Y�B�QJ.Vd��F	�L�EI�(��h�����6Q�q3p�*���Q2.�eQ7���e]���Q2nf����C�2�CDc�4�A(��k�~HD�Rp�8�Dd��U���":F��g�P��Y'o�g��������Qb.Wh]�j����h�Ј�qh�h���[Nc�H�E䌮V���!j\W'���1���~0ĉ�q�P�B�8���R�q��!�Z?4�D��^�uj�f�k�Z?"�uR����:��������ͩ�j�Z?^��3��ǷL�c6r'�;J~^�y���O��E.�up';��ጽq�$"'��yq�|&�zv�V}�
��I�啌RxB���I%�ս֠���5xs��j��R� ;��9�z�iB��8)1�/�������>9��v{�ONJ�����n9���;��]r2����9M���O���Nf�`g�LWnW�k;�b_��@nU�f=.�Č� 2�4��ɸv��{�D]$��o{��'2���A8�"�ǉD��T�`����j�����D��UɁPþ8)-��°����{�=$�b�b��f�|���;�D�B��{�'U�W�B1ib��8�� �b��]r���G�C�����.�Q����UӃ�`��u
n�3'��԰>�9'��!!��a�f��9���J=��Ջ��bجv�-����BrK+�R5Bz��SN��C�PU�cל�@͐IS�1'!�V�?���+'� xE��=s�$q���K�A'��HN��D��	�0�b��	"3�`ߜd��jB%C��z��qb��7�C'���\v6�E'U׃$��4�G'K� Jr���:YN�ɍ1�O'+�b՟�XAĉ�7��m��N6���f��~:��C��Ԗ Vl����� )<�������w��^��vҩǷ]k�~p줓�W)"��1�A'=ү�Ŧd� bE�ۯ[ۏE�So!A�"�ө���vթ�顪1�e'=�o3;���]k���:]��C���4hD�Q-�1�[����Q�5�i��#u�^��D:샤j��"R�ȟjH� "e�Q�:Pg�~�?�$��D�c���@Jr+��#ƙ�w������}��5�#�*1���L���:�nH�49�Udn�J�Q·�fj����P*o�Ѣ�o��2c�%�J�-A��Q�h��D���j�����4҃�^�!gk�n9�?�5�L#G9���G�����$̬F��Q��j���u���ђ,Hb�w���Da���Յ�Ja�G�����׷:��A�<8�\c=�o*����z�_%��z�z�h;e�6{�C?$�������U"zF�*��\g�~�6����:k����\E|�k����@��׵����];��~H�0�fW�����퉼�U[���ڤ��]��:�j#/w5��*rv5|�F@K}��=/�}����DM�E��)�|��>+�Q��Y�߫0����f@}}�j>k#���@%��<T}�Z>��7���3�*L����$-���L���@��������������~>ݐ/�/P�gm�&��y�)___��ό�ʢuD��y�U�ZET���WiP�g��*�/���Y��Q�g��U���]YP_��ό����~f�X�]T�Y���Yi�~��eYT_���sY���Y�c����Ĝ�e���R_����*��/P�g��˫
 �  ������4�=��B ۮ�� ��٢��l�	] lS��}f$t���%�H�`:0������ɂ��6��,YEĐE/f�:"r�XQ_� ؆>̺���	�}̍��x	� ܣ��� �p:1k�+��Џ�__:�GOfn}A���ei���}��,L��
�>�e���n �c���� �џ����!˛�ZG��`�BW k���� t��T_:x@fa��C���y�������<$=�Y��ЫYČ��!�k�"�x���̈ѣY�,�%�#z5�V�<�W�l9�����qۧYT_�c���fb1#�5����[{:xݛ�zءg3��@� ;tm�]��}��i}����mA� ;tn���C�fV��c��6K��㆖����\��Nv1�f�1 �7=҅.��B׀t�����@�t1�fѮ�s@:�q1#:�ݜE����B7g&3�s@:tp'tH�NβU�_x١��h�9 ���/�) &�qV���sf%^���9:����5��Il�_���Ln�Ơ�3����zX�tuf�6�ƴ�;)�bM�߹�-��K1�gaʈ��XBgV�G~O,e9<S��E����$���;	�B��$t��;	=Bm�NB׀P����; ���$t��;	��-�NB߀p����9 ���$t��;	��m�NB�p����) �0�NB׀p����3 ���$��<����9 R��$����;	�"��NB�H����7 ���$����;	="u�NBǀH����1 ���d����w2:�>����1 ��������dt�m�w2zĶ�;�b������-�NF߀�:'�s@l����5 }����; }����9 }����) ���;]���;=ҷ�;=�?����9 }����7 }����/ }����9 C����7 C����7 C����C C����1 C����1 C����/ C����) �C�;2��;=2��;]2��;�2��;�2��;�2��;}2��;�2��;]2��;�2��;�2��;�2>����5 ��������d��{'�s@\����7 ��������dt�k�w2�ĵ�;}�����qu�NFǀ�Z���Ҿ�꬘����ʊ�(�ۮՊ�(�ۮ�-�1����O>�ݓG���G���      �   �
  x�5�ۭ)�M0��+��?���ܕ8R�4�y��fǎ�}�ĉ�{�qc��^��s�ay[�Ayw�=�f�X�a�M�4��)�2v�T�jb���h��em*ܠ��V_��/(}��F�z����o3(�#��ߢ�

���C[�/���C[I����;����كB���J�3�����l3����qa�!��Й�,b+�k������/(��_�/(}}�eJ�� GX�T2y�/[fPΣ͊td��
6O�C}�zQ�2��j�����#��	���a�\#���x��9�����>j��$�[;��G��	��O��x�������/�.���{��gP޸mgP���b��:�wP����	�9�m���;,�~A�ޝ/�C������AY�?3(}3y��sL��������AYI��|��n<�<
kw������Ay{���9$�~gP��<fP�����rލ��W^�����bg�O/(�|�}A���o�~g�7�-:L���M�����Ƿ�����lo�7B��1u>�"1��S���G0�n�ˑX�(%ώ�KÚ�n|��Et�ϘT�>%5���0����]��g�tzt}#ET2��P��ļ�P��F��=}��'@$Czmo�i\�@�f0���۪�#M�c�0KQ���C|��|��N}�1�~�E0j�Y>_8�lG֣�)#��JV�C�aW�1hL�	���]̨�W�L���r��A,�)�Aڨ{��q��w��.����m���n>�.��OW Oih>6h�6�sX�R]����Ƣ�l�q���ȅZ6X�;��[���Bz.��pxK'��I�,R���(�����tfI�t��/����D:�����1C\8�w�݉gD�i�������ӂ
��]��5cBJm����6I���G�׾�CZ��n��M���ͅw�ɬ#��傫���]�a�i�M]��r�� L���I���_�>;�b���c��+Avk�����ݓ�
�?㷜M��v�n6{�����w����XS���a']��~5��ԏm�
�����G�5ph�P��߰A�"H8Iw����a
ǧ�i����$ԅ��)v��8i��DN����S�Y��k}���<�xr�`� G\�����Y7��m����T-�M�m	�
Z��7U��0bR��a�$�fR��&�xƼx� �0to��'���a�$����/���ls����8\ЦX���H�ۨH�3tL��ȣ��.��Sܔ�f �ҵ��L�6���t�)\��Md`-�A�V�/ �bw� �í@b[����T�2��p|�P�BN��s},OTc�a[Ʌ�mh^���74ǐ��b	�e`�������hօ�4lS��J�<~A.���g��@�b���8�g/�m
|��m�Lx9l��}~�M�&�)�n\��}�h�)�Ma��m"��g�6�!� m��Ɖ1`��2b�L���9lb��C��T��Nz��9��K�:�m���sئ	l+��qu6���ؾ�R�k�p��Ø�		lS�c~ ��N�T���)V�a 7�z lS9��"��g��Q�sy���n�g�¢��t�ȑ-��RzF~�m����s�����t�zLЦ�^��Y�6A܄m��c��H�m���G?&tS�~t�%O5�r��YqK6��,�-�a]ƭa~���
��)��G�6+|��|�p��z�q6o*��	���y�L�y��[�XM�|�F�>��ze�fm�MU4��F]/�.��i�8;�`�(�p�i�X��䛕+֡�q�E)W��mg�h�XQܴ9��s|�ʕ2i4~sB�۬μ��:�'tSf�)v���T�;�	���/0�����k�q1�n��8�)�b��V�&���w����Α��'+�);����sR�6+������g��zz/|S��5�z������,�A�	��q��m�RM[��s�]���%�F�y�Ĳ���n�Y�)v�����/7�k����wB7e�9���n��-��NgE���ڶƮ���
�t+ئ��i~�T�mB�V�(�a�:N'hS�i������77'��QT�g1n�7u9+'lS�b�g1~g�n%��p�pSu�3��2-��M�ik�����+��{��ά��$pS�c�cx{��/��%!�����V!{9?/0V�pBVT���-+S��)(�3P/�y%�i#�.�{A��}�t��0���g�\9�`�|�<c�לc�e�w�$����RhS�|K���^�m9��f6����9h�JR�	�֜�3�z��ie�鷮�|����a[V���My}��M�a����	ڔ3�Ǚa�7W����8�sq���<k�~�Fq�4��o�1y/���Xֱ�3�s�ܢ.��P�R�~��F�Yv������rT[`;hM���Ӕ>�ɶ�!�pM��\�)���c�ޛ��&�quUo�D� %�1�1l�^T�h��J�g&��f��5o�0_i*$IЦ��a�Z���b8]��/M��׃F��¾q�3�>ԩ:X���1n�*�t)&�g/#+?����\s��)V�j7�#J�R�j�w�Ɛ>c[�M���
��)�O7Uaw���6Gx�:�w���M����7��pS�q׫�����A��۲��l�0`r*��hS]f���O<cU�Յ.hSƋ	�T7�Lؖu'}�7��w�>�f�+F��M�<���=����]���P��L;V<���
!فaj��?��� xٕ      �   �  x��W�NI}n�ߣE}���~��D�hW�a!RD���)o�[ll~���r�z,L`�s��:sꜪ�J"��m���8Χ�K����QޖC<����2��2o�Q��y��_"��]��,3�"$g�4�$)�pNJy ���-f��q^�M��j��r������pR8��(�7<�!$��"z�DZ�i>��%�nGP���IjaQX'�A�@��pI�/�	M��w�y�<<K2H����D���^0�0�2���X�e��=��� ���I	׈A'`��`E��rOQ0w�W���]��t�ǋ�(�c��{�̊�CV�B�}�P2.q;#6�b4#�H��iV��ClMllwٸ W �z�?�����o�a0׸9���ON�88'�������u�G���@%�Ձ5~A��[5��G8U�1b ��	���M������H��?@+��p�j����B�<z�Nd�֬S�Ģzx���H�m�AV(�+�����8���S��m���P@Έdh1��]�.��YZ ���S�Y$V�rB#6T�����5��oҽ�çbJ�+�qj6������%Wq���͌n�8�=W9z�ɝ��\���]�l�֚5�6C�Њ����`ߎ��q�k��WV�C`T0���8�F0�����>�ye�UDK��h�&m�yBk%�}d�k4����(�Y&��5�/��˯�
�&�*)�c(M���.\���~���s-�wK�(�t4&���
jP-��_���@P��l�L�Ι=�1�[T"5�r(t�s��З��T{��	�����8�������6
J����NE�m�wv*���A��:HlD��g�'����kZ����ΫGvPVas�M�l���a_)�	:�Я�Rq�?ҵa#SҮ�k�A(���=/�:cu1�_n*+�D���m�};�RH(�<�L&� ��>�      �   �   x�m�1
�0��9E�dQJ_^ڦwq��z����D�@P�m��r#_J���M�_TTo�zۨhYVP�y���"Z���o*�J֝����n?��Re�a�� :0�3���B5g.�v;�9\�1��6�70��kr���ܸ����zw����i�'jO@ �	c̟����9�0`%��cP�X�B��bk�      �   E   x���� ��QLF85�K��#�`^�8�b���$���N�Х6<�ä4fi�ax�LND|�i��      �   @   x�=��  �w�Pwq�9TH�qm!m�P�r��|�͌Z��E�>ˁlz=;��"r �5h      �   �   x�����@Ek�^��� ��Ð��� $h!�	JP�f������ƒ����')e�7�ѐ�M�5>.*t��%z)F@�,Ň�GaJ�)T��ү@F��m@'+��R酀+eQ�~�38����=j�ξ����~>�q>O�1/\��H      �   �  x�mV�n�F}&?&誾�m_�����`�] @^81�&tဒ�(_��Z+XKl�,��s)��^��uXN�q����y:�u^��_��r���χ.�5E�'j��Tt�%�ll��#��J���X;]Y�ˎ�M��<|�ֹ���_���n:�?��eX��3Q��}:(YRZrRl�L�܌qJ��O-����g�tyǳ���u�:����{��8��T'�;���^��xnIE�|��+1��Ԓo�[a�+e�c	�H�7�Ӱ�����˼<��=/&��e#���h>�B�9.�k�I��m�DIRv�%�E��{�=� ���1N���ļ��y�-�s)�1���`Z\2YH�l��̀}.^�1�Ž��|��i���=�#3|���N��TW$ǒY���<	��B2�H[S���F�w(�}�e�>�߇u����8=�C�@�q���rgR1���l�$/)&�
�2� ���,�ߠ�ݏ@���0F,n��~|����h��LxU�B"�%(��m�Z�m�[Øu�ڴ�V֔jC�:+�����}v�3F7�2/��p����'��a���٘ 2�j��e�'`������j��oF
��TO��y���o��q����"? J�R*c'F��Ҩ�J�DP`�,5�����R���n��#ƞ78Y��,9�Q#�dH8�/Z����f�
4N>#D����M�$�57j�'�~���@j8>/�B��8������G���j-@4�rY	kWQZL����{҈����i�^���{<����-��+�C2�H0�fC� E��('e�o1����x["I���>��q}���ɭf�$&�I���Q�I�f]+ec�+�Z"r�%Mb���l����~g��x���,iŸ��TTlA�D6P�j$����!����`X�v�6Lr=��]���y>����x���޷Z�ۚ�H�'�4�r��A�H���J��-��k�#nn�O�'d
�8���妕�5����ĕ��T
��@�*do���B�x3�$�PG��몹|�4=���<?-j�~{�q�л�ʹDu^L�X�Ȕ[�.X�N���mn� ��o�ٿ�tO��4�߾���a:/�e8���E�Ԣ&�JPw5�
A�c�Q�jHg{���Ipb���vkճ�?1��z�����^��cJ1�R� 3X���m%�-�ZDpe���� =������J��3uOo��e+��x��ݡ��OH��������|��8ȅ������n��No��e�.��M����v�|�����z������`�^��ºn���j�55�L^;$�/j���W�yv%��=���2��o����e��vT���b�.i奴��F�F�R���N9f*�T&�=F�ɸ�ܳ�P�ӈ�0��6�^f�9n�7y�<� g�#�<J���S+�8%D\5���z�]�W�h�ex�����������pL@4(@+[Y�����$jܦ8ԦBď���c��O=;��;t�����iK�/���"�c"{�Nq(\0UbD!|*����U�C�ܐڄ�u���2.W�[�|BY��;W2�T!{�*R���n7SA��
ȕB(֒�T*� ��o�[�yt=����y�퇾���GN:     