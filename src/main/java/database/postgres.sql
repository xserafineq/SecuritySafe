PGDMP      !                }           postgres    17.4    17.2 7    _           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            `           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            a           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            b           1262    5    postgres    DATABASE     n   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'pl-PL';
    DROP DATABASE postgres;
                     postgres    false            c           0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                        postgres    false    4962                        2615    25421    safe    SCHEMA        CREATE SCHEMA safe;
    DROP SCHEMA safe;
                     postgres    false            �            1259    25464 
   hyperlinks    TABLE     �   CREATE TABLE safe.hyperlinks (
    hyperlink_id integer NOT NULL,
    url text NOT NULL,
    last_modified timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    title character varying(100) NOT NULL,
    safe_id integer
);
    DROP TABLE safe.hyperlinks;
       safe         heap r       postgres    false    5            �            1259    25463    hyperlinks_hyperlink_id_seq    SEQUENCE     �   CREATE SEQUENCE safe.hyperlinks_hyperlink_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE safe.hyperlinks_hyperlink_id_seq;
       safe               postgres    false    5    224            d           0    0    hyperlinks_hyperlink_id_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE safe.hyperlinks_hyperlink_id_seq OWNED BY safe.hyperlinks.hyperlink_id;
          safe               postgres    false    223            �            1259    25493 
   login_logs    TABLE     �   CREATE TABLE safe.login_logs (
    session_id integer NOT NULL,
    login_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    ip_address text NOT NULL,
    status character varying(50),
    user_id integer NOT NULL
);
    DROP TABLE safe.login_logs;
       safe         heap r       postgres    false    5            �            1259    25492    login_logs_session_id_seq    SEQUENCE     �   CREATE SEQUENCE safe.login_logs_session_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE safe.login_logs_session_id_seq;
       safe               postgres    false    228    5            e           0    0    login_logs_session_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE safe.login_logs_session_id_seq OWNED BY safe.login_logs.session_id;
          safe               postgres    false    227            �            1259    25479    notes    TABLE     �   CREATE TABLE safe.notes (
    note_id integer NOT NULL,
    content text NOT NULL,
    safe_id integer,
    title character varying(150) NOT NULL
);
    DROP TABLE safe.notes;
       safe         heap r       postgres    false    5            �            1259    25478    notes_note_id_seq    SEQUENCE     �   CREATE SEQUENCE safe.notes_note_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE safe.notes_note_id_seq;
       safe               postgres    false    5    226            f           0    0    notes_note_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE safe.notes_note_id_seq OWNED BY safe.notes.note_id;
          safe               postgres    false    225            �            1259    25449 	   passwords    TABLE     #  CREATE TABLE safe.passwords (
    password_id integer NOT NULL,
    password character varying(300) NOT NULL,
    login character varying(200) NOT NULL,
    name character varying(100) NOT NULL,
    last_modified timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    safe_id integer
);
    DROP TABLE safe.passwords;
       safe         heap r       postgres    false    5            �            1259    25448    passwords_password_id_seq    SEQUENCE     �   CREATE SEQUENCE safe.passwords_password_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE safe.passwords_password_id_seq;
       safe               postgres    false    5    222            g           0    0    passwords_password_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE safe.passwords_password_id_seq OWNED BY safe.passwords.password_id;
          safe               postgres    false    221            �            1259    25435    safes    TABLE     X   CREATE TABLE safe.safes (
    safe_id integer NOT NULL,
    user_id integer NOT NULL
);
    DROP TABLE safe.safes;
       safe         heap r       postgres    false    5            �            1259    25434    safes_safe_id_seq    SEQUENCE     �   CREATE SEQUENCE safe.safes_safe_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE safe.safes_safe_id_seq;
       safe               postgres    false    220    5            h           0    0    safes_safe_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE safe.safes_safe_id_seq OWNED BY safe.safes.safe_id;
          safe               postgres    false    219            �            1259    25423    users    TABLE     m  CREATE TABLE safe.users (
    user_id integer NOT NULL,
    email character varying(150) NOT NULL,
    password character varying(300) NOT NULL,
    join_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT users_email_check CHECK (((email)::text ~~ '%@%'::text)),
    CONSTRAINT users_password_check CHECK ((length((password)::text) > 5))
);
    DROP TABLE safe.users;
       safe         heap r       postgres    false    5            �            1259    25422    users_user_id_seq    SEQUENCE     �   CREATE SEQUENCE safe.users_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE safe.users_user_id_seq;
       safe               postgres    false    5    218            i           0    0    users_user_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE safe.users_user_id_seq OWNED BY safe.users.user_id;
          safe               postgres    false    217            �           2604    25638    hyperlinks hyperlink_id    DEFAULT     ~   ALTER TABLE ONLY safe.hyperlinks ALTER COLUMN hyperlink_id SET DEFAULT nextval('safe.hyperlinks_hyperlink_id_seq'::regclass);
 D   ALTER TABLE safe.hyperlinks ALTER COLUMN hyperlink_id DROP DEFAULT;
       safe               postgres    false    223    224    224            �           2604    25639    login_logs session_id    DEFAULT     z   ALTER TABLE ONLY safe.login_logs ALTER COLUMN session_id SET DEFAULT nextval('safe.login_logs_session_id_seq'::regclass);
 B   ALTER TABLE safe.login_logs ALTER COLUMN session_id DROP DEFAULT;
       safe               postgres    false    227    228    228            �           2604    25640    notes note_id    DEFAULT     j   ALTER TABLE ONLY safe.notes ALTER COLUMN note_id SET DEFAULT nextval('safe.notes_note_id_seq'::regclass);
 :   ALTER TABLE safe.notes ALTER COLUMN note_id DROP DEFAULT;
       safe               postgres    false    226    225    226            �           2604    25641    passwords password_id    DEFAULT     z   ALTER TABLE ONLY safe.passwords ALTER COLUMN password_id SET DEFAULT nextval('safe.passwords_password_id_seq'::regclass);
 B   ALTER TABLE safe.passwords ALTER COLUMN password_id DROP DEFAULT;
       safe               postgres    false    221    222    222            �           2604    25642    safes safe_id    DEFAULT     j   ALTER TABLE ONLY safe.safes ALTER COLUMN safe_id SET DEFAULT nextval('safe.safes_safe_id_seq'::regclass);
 :   ALTER TABLE safe.safes ALTER COLUMN safe_id DROP DEFAULT;
       safe               postgres    false    219    220    220            �           2604    25643    users user_id    DEFAULT     j   ALTER TABLE ONLY safe.users ALTER COLUMN user_id SET DEFAULT nextval('safe.users_user_id_seq'::regclass);
 :   ALTER TABLE safe.users ALTER COLUMN user_id DROP DEFAULT;
       safe               postgres    false    217    218    218            X          0    25464 
   hyperlinks 
   TABLE DATA           T   COPY safe.hyperlinks (hyperlink_id, url, last_modified, title, safe_id) FROM stdin;
    safe               postgres    false    224   �>       \          0    25493 
   login_logs 
   TABLE DATA           W   COPY safe.login_logs (session_id, login_time, ip_address, status, user_id) FROM stdin;
    safe               postgres    false    228   �@       Z          0    25479    notes 
   TABLE DATA           ?   COPY safe.notes (note_id, content, safe_id, title) FROM stdin;
    safe               postgres    false    226   >A       V          0    25449 	   passwords 
   TABLE DATA           ]   COPY safe.passwords (password_id, password, login, name, last_modified, safe_id) FROM stdin;
    safe               postgres    false    222   �A       T          0    25435    safes 
   TABLE DATA           /   COPY safe.safes (safe_id, user_id) FROM stdin;
    safe               postgres    false    220   �B       R          0    25423    users 
   TABLE DATA           B   COPY safe.users (user_id, email, password, join_date) FROM stdin;
    safe               postgres    false    218   �B       j           0    0    hyperlinks_hyperlink_id_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('safe.hyperlinks_hyperlink_id_seq', 16, true);
          safe               postgres    false    223            k           0    0    login_logs_session_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('safe.login_logs_session_id_seq', 12, true);
          safe               postgres    false    227            l           0    0    notes_note_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('safe.notes_note_id_seq', 5, true);
          safe               postgres    false    225            m           0    0    passwords_password_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('safe.passwords_password_id_seq', 8, true);
          safe               postgres    false    221            n           0    0    safes_safe_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('safe.safes_safe_id_seq', 3, true);
          safe               postgres    false    219            o           0    0    users_user_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('safe.users_user_id_seq', 4, true);
          safe               postgres    false    217            �           2606    25472    hyperlinks hyperlinks_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY safe.hyperlinks
    ADD CONSTRAINT hyperlinks_pkey PRIMARY KEY (hyperlink_id);
 B   ALTER TABLE ONLY safe.hyperlinks DROP CONSTRAINT hyperlinks_pkey;
       safe                 postgres    false    224            �           2606    25501    login_logs login_logs_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY safe.login_logs
    ADD CONSTRAINT login_logs_pkey PRIMARY KEY (session_id);
 B   ALTER TABLE ONLY safe.login_logs DROP CONSTRAINT login_logs_pkey;
       safe                 postgres    false    228            �           2606    25486    notes notes_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY safe.notes
    ADD CONSTRAINT notes_pkey PRIMARY KEY (note_id);
 8   ALTER TABLE ONLY safe.notes DROP CONSTRAINT notes_pkey;
       safe                 postgres    false    226            �           2606    25457    passwords passwords_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY safe.passwords
    ADD CONSTRAINT passwords_pkey PRIMARY KEY (password_id);
 @   ALTER TABLE ONLY safe.passwords DROP CONSTRAINT passwords_pkey;
       safe                 postgres    false    222            �           2606    25440    safes safes_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY safe.safes
    ADD CONSTRAINT safes_pkey PRIMARY KEY (safe_id);
 8   ALTER TABLE ONLY safe.safes DROP CONSTRAINT safes_pkey;
       safe                 postgres    false    220            �           2606    25442    safes safes_user_id_key 
   CONSTRAINT     S   ALTER TABLE ONLY safe.safes
    ADD CONSTRAINT safes_user_id_key UNIQUE (user_id);
 ?   ALTER TABLE ONLY safe.safes DROP CONSTRAINT safes_user_id_key;
       safe                 postgres    false    220            �           2606    25433    users users_email_key 
   CONSTRAINT     O   ALTER TABLE ONLY safe.users
    ADD CONSTRAINT users_email_key UNIQUE (email);
 =   ALTER TABLE ONLY safe.users DROP CONSTRAINT users_email_key;
       safe                 postgres    false    218            �           2606    25431    users users_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY safe.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);
 8   ALTER TABLE ONLY safe.users DROP CONSTRAINT users_pkey;
       safe                 postgres    false    218            �           2606    25473 "   hyperlinks hyperlinks_safe_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY safe.hyperlinks
    ADD CONSTRAINT hyperlinks_safe_id_fkey FOREIGN KEY (safe_id) REFERENCES safe.safes(safe_id);
 J   ALTER TABLE ONLY safe.hyperlinks DROP CONSTRAINT hyperlinks_safe_id_fkey;
       safe               postgres    false    224    220    4784            �           2606    25502 "   login_logs login_logs_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY safe.login_logs
    ADD CONSTRAINT login_logs_user_id_fkey FOREIGN KEY (user_id) REFERENCES safe.users(user_id);
 J   ALTER TABLE ONLY safe.login_logs DROP CONSTRAINT login_logs_user_id_fkey;
       safe               postgres    false    218    228    4782            �           2606    25487    notes notes_safe_id_fkey    FK CONSTRAINT     x   ALTER TABLE ONLY safe.notes
    ADD CONSTRAINT notes_safe_id_fkey FOREIGN KEY (safe_id) REFERENCES safe.safes(safe_id);
 @   ALTER TABLE ONLY safe.notes DROP CONSTRAINT notes_safe_id_fkey;
       safe               postgres    false    4784    226    220            �           2606    25458     passwords passwords_safe_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY safe.passwords
    ADD CONSTRAINT passwords_safe_id_fkey FOREIGN KEY (safe_id) REFERENCES safe.safes(safe_id);
 H   ALTER TABLE ONLY safe.passwords DROP CONSTRAINT passwords_safe_id_fkey;
       safe               postgres    false    4784    222    220            �           2606    25443    safes safes_user_id_fkey    FK CONSTRAINT     x   ALTER TABLE ONLY safe.safes
    ADD CONSTRAINT safes_user_id_fkey FOREIGN KEY (user_id) REFERENCES safe.users(user_id);
 @   ALTER TABLE ONLY safe.safes DROP CONSTRAINT safes_user_id_fkey;
       safe               postgres    false    218    220    4782            X   �  x���K��@�5�
7��o���|�_��M��X���Lf�,���η8��&(&�%�dN��9�]���;ó��\`y������/�z��iK4�P���,����=��LO����?W��XaNm[� (%��<B��	�cJ���J_xK�t����o�kmq��q6T��S�%�W�_G&_��P����n6(����Ha k}8�I���z,�������R:nhW4>OI�B���Xͯ��L�Jň7z��n��"pOǳYdڷ���뢒WN�yc�U
lo|�h۹ݺ����$mu��@_��Y��p�,=�($"*G�$+{�94H6�u%P����ew	k�p�4��R�W^�+/膊�}��~�#�y���_@��R��������P��q�s�p��a;�z [Q �T׿��Gޖ�~��}��W�@�Ӕ��^.�9��j��/�x��mqo�9bY�rN�      \   ^   x��ʱ	�@�:��.$�O;A\���Pl���+���G�(��� ],�D�ѱ��CϹ__�/�7���o|_~=S4+���e^�������X      Z   �   x�L=� ���6Zu�:��f�|�/)�406�DA{/�oyy�;v�4��jZ�yt��@>!���=,�|���l�}�-��h��7� ���b�vV�*a��&

]�#�0�<�B���}i(�)�xX��$С��ږ�=�K���>�      V   �   x�u��
�0�s��Z֬�N{���i�jc��N=8� �|�@j�>j��M��UQ\=t�1,Q�J�B�(��;����@]"��"�o=4]�ދ6`�Q�3F���hD�/8j�1�ˌ�A�e��5[���؍76�^����df��2ݳ���R�z��=���&Oo����9}���ً�(�V�]�      T      x�3�4�2�4�2�4����� H      R   �   x�m��N�@@�5<���̤�B.��b��6m�`�[�_�`��5&�.L��;Ԃbl���7e9�p(q����e���4�򏟛�=es����_�2��? ^0��g���"��'�Y������h$2*���
���=��~�5O���vrфU{L�em�0k�W��eZd�w�}���V�S���Rh����p_���Io�i�T��-���<�Q�%��M��ߠ�⋋��l��dQT�     