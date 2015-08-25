package com.webonise.assignment11.asynctaskplusvolly;

import java.util.List;

/**
 * Created by webonise on 24/8/15.
 */
public class JsonDataParser {
    /**
     * contacts : [{"id":"c200","phone":{"office":"00 000000","home":"00 000000","mobile":"+91 0000000000"},"address":"xx-xx-xxxx,x - street, x - country","email":"ravi@gmail.com","name":"Ravi Tamada","gender":"male"},{"id":"c201","phone":{"office":"00 000000","home":"00 000000","mobile":"+91 0000000000"},"address":"xx-xx-xxxx,x - street, x - country","email":"johnny_depp@gmail.com","name":"Johnny Depp","gender":"male"},{"id":"c202","phone":{"office":"00 000000","home":"00 000000","mobile":"+91 0000000000"},"address":"xx-xx-xxxx,x - street, x - country","email":"leonardo_dicaprio@gmail.com","name":"Leonardo Dicaprio","gender":"male"},{"id":"c203","phone":{"office":"00 000000","home":"00 000000","mobile":"+91 0000000000"},"address":"xx-xx-xxxx,x - street, x - country","email":"john_wayne@gmail.com","name":"John Wayne","gender":"male"},{"id":"c204","phone":{"office":"00 000000","home":"00 000000","mobile":"+91 0000000000"},"address":"xx-xx-xxxx,x - street, x - country","email":"angelina_jolie@gmail.com","name":"Angelina Jolie","gender":"female"},{"id":"c205","phone":{"office":"00 000000","home":"00 000000","mobile":"+91 0000000000"},"address":"xx-xx-xxxx,x - street, x - country","email":"dido@gmail.com","name":"Dido","gender":"female"},{"id":"c206","phone":{"office":"00 000000","home":"00 000000","mobile":"+91 0000000000"},"address":"xx-xx-xxxx,x - street, x - country","email":"adele@gmail.com","name":"Adele","gender":"female"},{"id":"c207","phone":{"office":"00 000000","home":"00 000000","mobile":"+91 0000000000"},"address":"xx-xx-xxxx,x - street, x - country","email":"hugh_jackman@gmail.com","name":"Hugh Jackman","gender":"male"},{"id":"c208","phone":{"office":"00 000000","home":"00 000000","mobile":"+91 0000000000"},"address":"xx-xx-xxxx,x - street, x - country","email":"will_smith@gmail.com","name":"Will Smith","gender":"male"},{"id":"c209","phone":{"office":"00 000000","home":"00 000000","mobile":"+91 0000000000"},"address":"xx-xx-xxxx,x - street, x - country","email":"clint_eastwood@gmail.com","name":"Clint Eastwood","gender":"male"},{"id":"c2010","phone":{"office":"00 000000","home":"00 000000","mobile":"+91 0000000000"},"address":"xx-xx-xxxx,x - street, x - country","email":"barack_obama@gmail.com","name":"Barack Obama","gender":"male"},{"id":"c2011","phone":{"office":"00 000000","home":"00 000000","mobile":"+91 0000000000"},"address":"xx-xx-xxxx,x - street, x - country","email":"kate_winslet@gmail.com","name":"Kate Winslet","gender":"female"},{"id":"c2012","phone":{"office":"00 000000","home":"00 000000","mobile":"+91 0000000000"},"address":"xx-xx-xxxx,x - street, x - country","email":"eminem@gmail.com","name":"Eminem","gender":"male"}]
     */
    private List<ContactsEntity> contacts;

    public void setContacts(List<ContactsEntity> contacts) {
        this.contacts = contacts;
    }

    public List<ContactsEntity> getContacts() {
        return contacts;
    }

    public static class ContactsEntity {
        /**
         * id : c200
         * phone : {"office":"00 000000","home":"00 000000","mobile":"+91 0000000000"}
         * address : xx-xx-xxxx,x - street, x - country
         * email : ravi@gmail.com
         * name : Ravi Tamada
         * gender : male
         */
        private String id;
        private PhoneEntity phone;
        private String address;
        private String email;
        private String name;
        private String gender;

        public void setId(String id) {
            this.id = id;
        }

        public void setPhone(PhoneEntity phone) {
            this.phone = phone;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getId() {
            return id;
        }

        public PhoneEntity getPhone() {
            return phone;
        }

        public String getAddress() {
            return address;
        }

        public String getEmail() {
            return email;
        }

        public String getName() {
            return name;
        }

        public String getGender() {
            return gender;
        }

        public static class PhoneEntity {
            /**
             * office : 00 000000
             * home : 00 000000
             * mobile : +91 0000000000
             */
            private String office;
            private String home;
            private String mobile;

            public void setOffice(String office) {
                this.office = office;
            }

            public void setHome(String home) {
                this.home = home;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getOffice() {
                return office;
            }

            public String getHome() {
                return home;
            }

            public String getMobile() {
                return mobile;
            }
        }
    }
}
