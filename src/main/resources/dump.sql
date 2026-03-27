--
-- PostgreSQL database cluster dump
--



SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;


--
--
-- User Configurations
--









--
-- Databases
--

--
-- Database "template1" dump
--

\connect template1

--
-- PostgreSQL database dump
--



-- Dumped from database version 16.13
-- Dumped by pg_dump version 16.13

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- PostgreSQL database dump complete
--



--
-- Database "postgres" dump
--

\connect postgres

--
-- PostgreSQL database dump
--



-- Dumped from database version 16.13
-- Dumped by pg_dump version 16.13

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: usuario; Type: TABLE; Schema: public; Owner: ti2cc
--

CREATE TABLE public.usuario (
    codigo integer NOT NULL,
    login text,
    senha text,
    sexo character(1)
);





--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: ti2cc
--

COPY public.usuario (codigo, login, senha, sexo) FROM stdin;
\.


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (codigo);


--
-- PostgreSQL database dump complete
--



--
-- Database "teste" dump
--

--
-- PostgreSQL database dump
--



-- Dumped from database version 16.13
-- Dumped by pg_dump version 16.13

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: teste; Type: DATABASE; Schema: -; Owner: ti2cc
--

CREATE DATABASE teste WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';





\connect teste


SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: car; Type: TABLE; Schema: public; Owner: ti2cc
--

CREATE TABLE public.car (
    id integer NOT NULL,
    name text,
    placa text,
    type text,
    power integer
);




--
-- Name: car_id_seq; Type: SEQUENCE; Schema: public; Owner: ti2cc
--

CREATE SEQUENCE public.car_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- Name: car_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ti2cc
--




--
-- Name: usuario; Type: TABLE; Schema: public; Owner: ti2cc
--

CREATE TABLE public.usuario (
    codigo integer NOT NULL,
    login text,
    senha text,
    sexo character(1)
);



--
-- Name: car id; Type: DEFAULT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public.car ALTER COLUMN id SET DEFAULT nextval('public.car_id_seq'::regclass);


--
-- Data for Name: car; Type: TABLE DATA; Schema: public; Owner: ti2cc
--

COPY public.car (id, name, placa, type, power) FROM stdin;
6	Honda Civic	GTH-9012	JDM	340
4	Fiat Uno	AAS-9902	SUPER	10000
8	NISSAN SILVIA	JAP-123	S15	1200
\.


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: ti2cc
--

COPY public.usuario (codigo, login, senha, sexo) FROM stdin;
\.


--
-- Name: car_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ti2cc
--

SELECT pg_catalog.setval('public.car_id_seq', 9, true);


--
-- Name: car car_pkey; Type: CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_pkey PRIMARY KEY (id);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (codigo);


--
-- PostgreSQL database dump complete
--



--
-- PostgreSQL database cluster dump complete
--

