(this["webpackJsonpcr-web"] = this["webpackJsonpcr-web"] || []).push([[0], {
    50: function (e, t, c) {
    }, 51: function (e, t, c) {
    }, 75: function (e, t, c) {
    }, 88: function (e, t, c) {
    }, 89: function (e, t, c) {
    }, 90: function (e, t, c) {
    }, 91: function (e, t, c) {
    }, 92: function (e, t, c) {
    }, 94: function (e, t, c) {
    }, 95: function (e, t, c) {
        "use strict";
        c.r(t);
        var a = c(1), i = c.n(a), s = c(16), n = c.n(s), l = (c(50), c(2)), r = (c(51), c(10)), d = c(7), o = c(8),
            j = c.n(o), b = c(9), h = function (e) {
                return sessionStorage.getItem("user") ? {
                    type: "LOGIN",
                    payload: sessionStorage.getItem("user")
                } : (sessionStorage.setItem("user", e), {type: "LOGIN", payload: e})
            }, u = function () {
                return {type: "LOGOUT"}
            }, x = function () {
                return {type: "RESETDASH", payload: null}
            },
            p = "data:image/png;base64,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",
            O = (c(75), c(98)), m = c(0), g = Object(b.b)((function (e) {
                return {
                    vehicleID: e.data.vehicleID,
                    login: e.data.login,
                    logout: e.data.logout,
                    email: e.data.email,
                    registeredUser: e.data.registeredUser,
                    deletedUser: e.data.deletedUser,
                    newemail: e.data.newemail
                }
            }), {
                logins: h, reset: function () {
                    return {type: "RESET"}
                }
            })((function (e) {
                var t = e.logins, c = e.login, i = e.logout, s = e.newemail, n = e.deletedUser,
                    o = (e.vehicleID, Object(a.useState)("")), b = Object(l.a)(o, 2), h = b[0], u = b[1],
                    x = Object(a.useState)(!0), g = Object(l.a)(x, 2), f = g[0], v = g[1], y = Object(a.useState)(""),
                    N = Object(l.a)(y, 2), E = N[0], S = N[1], w = Object(a.useState)(!1), C = Object(l.a)(w, 2), I = C[0],
                    D = C[1], A = Object(a.useState)(""), R = Object(l.a)(A, 2), L = R[0], F = R[1], B = Object(r.g)();
                Object(a.useEffect)((function () {
                    "" != n && (F(!0), D(!1), S(!1)), !1 === c && !1 === i && (D(!0), S(!1), F(!1)), "" != s && (S(!0), D(!1), F(!1))
                }), []);
                return Object(m.jsx)("div", {
                    className: "App", children: Object(m.jsx)("div", {
                        className: "container",
                        style: {marginLeft: "270px", height: "100vh"},
                        children: Object(m.jsxs)("div", {
                            children: [Object(m.jsxs)("div", {
                                style: {lineHeight: "1.2", paddingTop: "100px"},
                                children: [Object(m.jsx)("img", {
                                    src: p,
                                    style: {marginBottom: "50px"}
                                }), Object(m.jsxs)("div", {
                                    style: {marginTop: "130px", lineHeight: "2"},
                                    children: [Object(m.jsx)("h1", {
                                        style: {fontWeight: "600", fontSize: "40px"},
                                        children: "Log In"
                                    }), Object(m.jsxs)("div", {
                                        className: "col-6-lg",
                                        style: {width: "600px"},
                                        children: [Object(m.jsx)(O.a, {
                                            color: "warning",
                                            style: {padding: "20px"},
                                            isOpen: I,
                                            children: "You have successfully logged out"
                                        }), Object(m.jsx)(O.a, {
                                            color: "warning",
                                            style: {padding: "20px"},
                                            isOpen: L,
                                            children: n
                                        }), Object(m.jsxs)(O.a, {
                                            color: "warning",
                                            style: {padding: "20px"},
                                            isOpen: E,
                                            children: ["Registration successful! You can now log in as `", s, "`."]
                                        }), f ? null : Object(m.jsxs)(O.a, {
                                            color: "warning",
                                            style: {padding: "20px"},
                                            children: ["Invalid user credentials.", Object(m.jsx)("br", {}), " If you aren't registered with MovR, go", " ", Object(m.jsx)(d.b, {
                                                to: "/register",
                                                type: "button",
                                                class: "",
                                                style: {color: "#FF3565"},
                                                children: "Sign Up"
                                            }), "!"]
                                        }), Object(m.jsx)("p", {
                                            style: {color: "#B2B3C2"},
                                            children: "Sign in to your account using email you provided during registration"
                                        }), Object(m.jsxs)("div", {
                                            className: "form-group",
                                            children: [Object(m.jsx)("label", {
                                                htmlFor: "exampleInputEmail1",
                                                children: "Email:"
                                            }), Object(m.jsx)("input", {
                                                onKeyDown: function (e) {
                                                    if (13 == e.keyCode) try {
                                                        j.a.post("/ui/auth/login", {email: h}).then((function (e) {
                                                            t(h), B.push("/vehicles")
                                                        })).catch((function (e) {
                                                            v(!1), D(!1), S(!1), F(!1)
                                                        }))
                                                    } catch (c) {
                                                        v(!1), D(!1), S(!1), F(!1)
                                                    }
                                                },
                                                required: !0,
                                                onChange: function (e) {
                                                    return u(e.target.value)
                                                },
                                                style: {height: "calc(1.5em + 1.125rem + 2px)"},
                                                type: "email",
                                                className: "form-control",
                                                id: "exampleInputEmail1",
                                                "aria-describedby": "emailHelp"
                                            })]
                                        }), Object(m.jsx)(d.b, {
                                            onClick: function (e) {
                                                if (e.preventDefault(), "" == h) v(!1), D(!1), S(!1), F(!1); else try {
                                                    j.a.post("/ui/auth/login", {email: h}).then((function (e) {
                                                        t(h), B.push("/vehicles")
                                                    })).catch((function (e) {
                                                        v(!1), D(!1), S(!1), F(!1)
                                                    }))
                                                } catch (c) {
                                                    v(!1), D(!1), S(!1), F(!1)
                                                }
                                            },
                                            to: "/vehicles",
                                            type: "button",
                                            className: "btn btn-danger form-control",
                                            style: {
                                                width: "100%",
                                                backgroundColor: "#FF3565",
                                                height: "calc(1.5em + 1.125rem + 2px)"
                                            },
                                            children: "Sign In"
                                        }), Object(m.jsxs)("p", {
                                            style: {
                                                padding: "10px",
                                                textAlign: "center",
                                                color: "#B2B3C2"
                                            },
                                            children: ["Don't have account?", " ", Object(m.jsx)(d.b, {
                                                to: "/register",
                                                class: "",
                                                style: {color: "#FF3565"},
                                                children: "Sign up"
                                            })]
                                        })]
                                    })]
                                })]
                            }), Object(m.jsx)("p", {
                                style: {
                                    position: "absolute",
                                    bottom: "0",
                                    marginLeft: "10px",
                                    fontSize: "14px",
                                    marginBottom: "40px",
                                    color: "#B2B3C2"
                                }, children: "\xa9 CockroachLabs"
                            })]
                        })
                    })
                })
            })), f = c.p + "media/user.svg", v = c.p + "media/chevron-down.svg";
        c(88);
        var y = Object(b.b)((function (e) {
            return {email: e.data.email}
        }), {logout: u})((function (e) {
            e.email;
            var t = e.logout, c = Object(r.g)();
            return Object(m.jsx)("div", {
                style: {width: "75%", margin: "0 auto"}, children: Object(m.jsxs)("nav", {
                    className: "navbar navbar-expand-sm navbar-light",
                    style: {paddingTop: "20px"},
                    children: [Object(m.jsx)("img", {
                        style: {marginLeft: "30px", marginRight: "25px"},
                        src: p,
                        height: 25,
                        width: 130
                    }), Object(m.jsx)("button", {
                        className: "navbar-toggler d-lg-none",
                        type: "button",
                        "data-toggle": "collapse",
                        "data-target": "#collapsibleNavId",
                        "aria-controls": "collapsibleNavId",
                        "aria-expanded": "false",
                        "aria-label": "Toggle navigation",
                        children: Object(m.jsx)("span", {className: "navbar-toggler-icon"})
                    }), Object(m.jsxs)("div", {
                        className: "collapse navbar-collapse",
                        id: "collapsibleNavId",
                        children: [Object(m.jsxs)("ul", {
                            className: "navbar-nav mr-auto mt-2 mt-lg-0",
                            id: "myDIV",
                            children: [Object(m.jsx)("li", {
                                className: "nav-item btn",
                                children: Object(m.jsx)(d.c, {
                                    activeStyle: {borderBottom: "2px solid black"},
                                    className: "nav-link text-dark",
                                    to: "/vehicles",
                                    children: "Vehicles"
                                })
                            }), Object(m.jsx)("li", {
                                className: "nav-item btn",
                                children: Object(m.jsx)(d.c, {
                                    activeStyle: {borderBottom: "2px solid black"},
                                    className: "nav-link text-dark",
                                    to: "/rides",
                                    children: "My Rides"
                                })
                            })]
                        }), Object(m.jsxs)("form", {
                            className: "form-inline my-2 my-lg-0",
                            style: {marginRight: "70px"},
                            children: [Object(m.jsx)(d.b, {
                                className: "btn btn-outline-danger my-2 my-sm-0 buttoncolor",
                                style: {textAlign: "center", fontWeight: "500", lineHeight: "1.5", borderRadius: "6px"},
                                to: "/addvehicles",
                                children: "+ New Vehicle"
                            }), " ", Object(m.jsxs)("span", {
                                style: {
                                    color: "grey",
                                    paddingLeft: "10px",
                                    paddingRight: "5px",
                                    fontSize: "25px"
                                }, children: [" ", "|", " "]
                            }), Object(m.jsxs)("a", {
                                className: "nav-item dropdown nav-link",
                                href: "/",
                                id: "navbarDropdown",
                                role: "button",
                                "data-toggle": "dropdown",
                                "aria-haspopup": "true",
                                "aria-expanded": "false",
                                children: [Object(m.jsx)("img", {src: f}), Object(m.jsx)("img", {src: v})]
                            }), Object(m.jsxs)("div", {
                                className: "dropdown-menu dropdown-menu-sm-right",
                                "aria-labelledby": "navbarDropdown",
                                children: [Object(m.jsx)(d.b, {
                                    to: "/profile",
                                    className: "dropdown-item",
                                    children: "Your Profile"
                                }), Object(m.jsx)("a", {
                                    className: "dropdown-item", onMouseDown: function () {
                                        t(), sessionStorage.clear(), c.push("/")
                                    }, children: "Logout"
                                })]
                            })]
                        })]
                    })]
                })
            })
        }));
        c(89);
        var N = Object(b.b)((function (e) {
            return {email: e.data.email, vehicleID: e.data.vehicleID}
        }), {
            startRides: function (e, t) {
                return function (c) {
                    try {
                        j.a.post("api/ride/start/", {email: e, vechicle_id: t}).then((function (e) {
                            alert("AS"), c({type: "STARTRIDE", payload: e})
                        })).catch((function (e) {
                        }))
                    } catch (a) {
                    }
                }
            }, resetDash: x
        })((function (e) {
            var t, c = e.email, i = e.vehicleID, s = (e.resetDash, Object(a.useState)([])), n = Object(l.a)(s, 2),
                r = n[0], o = n[1], b = Object(a.useState)(""), h = Object(l.a)(b, 2), u = h[0], x = h[1],
                p = Object(a.useState)(""), g = Object(l.a)(p, 2), f = g[0], v = g[1];
            null != i && (t = "NOT DELETED: " === i.slice(0, 13) ? 'DID NOT DELETE VEHICLE "' + i.slice(13) + '" BECAUSE IT IS CURRENTLY IN USE.' : "Deleted vehicle '" + i + "'."), Object(a.useEffect)((function () {
                j.a.get("/ui/vehicles?max_vehicles=20").then((function (e) {
                    o(e.data), v(null != i)
                })).catch((function (e) {
                }))
            }), [i]);
            var y = function () {
                j.a.post("/ui/rides/start", {email: c, vehicle_id: u}).then((function (e) {
                    console.log("Email : " + c)
                })).catch((function (e) {
                }))
            };
            return Object(m.jsxs)("div", {
                className: "container",
                style: {paddingLeft: "0px", paddingRight: "0px"},
                children: [Object(m.jsx)("h1", {
                    style: {margin: "20px", textAlign: "center"},
                    children: "Vehicles"
                }), Object(m.jsxs)(O.a, {
                    color: "warning",
                    style: {padding: "20px"},
                    isOpen: f,
                    children: ["'", t, "'"]
                }), Object(m.jsx)("p", {
                    className: "text-left",
                    children: "Below is a list of all* vehicles, their location, and their status."
                }), Object(m.jsx)("div", {
                    className: "row", children: r.map((function (e) {
                        return Object(m.jsx)("div", {
                            className: "col-lg-4 col-md-6",
                            style: {display: "flex", justifyContent: "center", marginBottom: "25px"},
                            children: Object(m.jsxs)("div", {
                                className: "card",
                                style: {width: "100%"},
                                children: [Object(m.jsx)("div", {
                                    className: "topboders",
                                    style: {height: "170px"}
                                }), Object(m.jsxs)("div", {
                                    className: "card-body",
                                    children: [Object(m.jsxs)("div", {
                                        style: {
                                            display: "flex",
                                            justifyContent: "space-between",
                                            marginBottom: "40px"
                                        },
                                        children: [Object(m.jsx)("div", {
                                            children: Object(m.jsx)("h4", {
                                                className: "card-title text-left text-capitalize",
                                                children: e.vehicle_info.type
                                            })
                                        }), Object(m.jsx)("div", {
                                            children: e.in_use ? Object(m.jsxs)("p", {
                                                className: "card-title text-right",
                                                children: [Object(m.jsx)("span", {
                                                    style: {color: "#FF8C00"},
                                                    children: "\u25cf"
                                                }), " ", "Unavailable"]
                                            }) : Object(m.jsxs)("p", {
                                                className: "card-title text-right",
                                                children: [Object(m.jsx)("span", {
                                                    style: {color: "lightgreen"},
                                                    children: "\u25cf"
                                                }), " ", "Available"]
                                            })
                                        })]
                                    }), Object(m.jsx)(d.b, {
                                        to: "/vehicledetail/".concat(e.id),
                                        className: "btn btn-danger",
                                        style: {
                                            width: "80%",
                                            backgroundColor: "#FF3565",
                                            height: "calc(1.5em + 1.125rem + 2px)"
                                        },
                                        children: "See Vehicle"
                                    }), Object(m.jsx)("hr", {}), Object(m.jsx)("p", {
                                        className: "text-left",
                                        children: "ID"
                                    }), Object(m.jsx)("p", {
                                        className: "card-text text-left",
                                        children: e.id
                                    }), Object(m.jsx)("hr", {}), Object(m.jsxs)("div", {
                                        style: {
                                            display: "flex",
                                            justifyContent: "start"
                                        },
                                        children: [Object(m.jsxs)("div", {
                                            className: "text-left",
                                            children: [Object(m.jsx)("div", {
                                                children: Object(m.jsx)("p", {
                                                    className: "card-text",
                                                    children: "Longitude"
                                                })
                                            }), Object(m.jsx)("div", {
                                                children: Object(m.jsx)("p", {
                                                    className: "card-text",
                                                    children: Number.isInteger(e.last_longitude) ? e.last_longitude + ".0" : e.last_longitude
                                                })
                                            })]
                                        }), Object(m.jsxs)("div", {
                                            className: "text-left",
                                            style: {marginLeft: "70px"},
                                            children: [Object(m.jsx)("div", {
                                                children: Object(m.jsx)("p", {
                                                    className: "card-text",
                                                    children: "Latitude"
                                                })
                                            }), Object(m.jsx)("div", {
                                                children: Object(m.jsx)("p", {
                                                    className: "card-text text-left",
                                                    children: Number.isInteger(e.last_latitude) ? e.last_latitude + ".0" : e.last_latitude
                                                })
                                            })]
                                        }), Object(m.jsx)("input", {type: "hidden", value: e.vehicle_id})]
                                    }), Object(m.jsx)("hr", {}), Object(m.jsxs)("div", {
                                        style: {
                                            display: "flex",
                                            justifyContent: "start"
                                        },
                                        children: [Object(m.jsxs)("div", {
                                            className: "text-left",
                                            children: [Object(m.jsx)("div", {
                                                children: Object(m.jsx)("p", {
                                                    className: "card-text",
                                                    children: "Color"
                                                })
                                            }), Object(m.jsx)("div", {
                                                children: Object(m.jsx)("p", {
                                                    className: "card-text text-capitalize",
                                                    children: e.vehicle_info.color
                                                })
                                            })]
                                        }), Object(m.jsxs)("div", {
                                            className: "text-left",
                                            style: {marginLeft: "100px"},
                                            children: [Object(m.jsx)("div", {
                                                children: Object(m.jsx)("p", {
                                                    className: "card-text text-left",
                                                    children: "Brand"
                                                })
                                            }), Object(m.jsx)("div", {
                                                children: Object(m.jsx)("p", {
                                                    className: "card-text",
                                                    children: e.vehicle_info.purchase_information.manufacturer
                                                })
                                            })]
                                        }), Object(m.jsx)("input", {type: "hidden", value: e.vehicle_id})]
                                    }), Object(m.jsx)("hr", {}), Object(m.jsx)("div", {
                                        style: {
                                            display: "flex",
                                            justifyContent: "start"
                                        },
                                        children: Object(m.jsxs)("div", {
                                            className: "text-left",
                                            children: [Object(m.jsx)("div", {
                                                children: Object(m.jsx)("p", {
                                                    className: "card-text",
                                                    children: "Battery"
                                                })
                                            }), Object(m.jsx)("div", {
                                                children: Object(m.jsxs)("p", {
                                                    className: "card-text",
                                                    children: [e.battery, "%"]
                                                })
                                            })]
                                        })
                                    }), " ", Object(m.jsx)("hr", {}), e.in_use ? null : Object(m.jsx)(d.b, {
                                        onClick: y,
                                        onFocus: function () {
                                            return x(e.id)
                                        },
                                        to: "/ridedetail/".concat(e.id, "/false"),
                                        className: "btn btn-danger",
                                        style: {
                                            width: "100%",
                                            backgroundColor: "#FF3565",
                                            height: "calc(1.5em + 1.125rem + 2px)"
                                        },
                                        children: "Start ride"
                                    })]
                                })]
                            })
                        }, e.id)
                    }))
                }), Object(m.jsx)("p", {children: "* The query is limited to the first 20 vehicles in the table to ensure quick rendering, but there may be many more rows in the table."})]
            })
        }));
        c(90);
        var E = Object(b.b)((function (e) {
            return {email: e.data.email, RideEndDetails: e.data.RideEndDetails}
        }), {
            endRides: function (e) {
                return function (e) {
                    e({type: "ENDRIDE"})
                }
            }, resetDash: x
        })((function (e) {
            e.rides, e.endRides;
            var t = e.email, c = e.RideEndDetails, i = e.resetDash, s = Object(a.useState)([]), n = Object(l.a)(s, 2),
                r = n[0], o = n[1];
            return Object(a.useEffect)((function () {
                j.a.get("/ui/rides?email=" + sessionStorage.getItem("user")).then((function (e) {
                    o(e.data)
                })), setTimeout((function () {
                    i()
                }), 1e4)
            }), []), r ? Object(m.jsxs)("div", {
                className: "container",
                style: {paddingLeft: "0px", paddingRight: "0px", minWidth: "1250px"},
                children: [Object(m.jsx)("h1", {
                    style: {margin: "20px", textAlign: "center", fontSize: "42px"},
                    children: "Rides"
                }), Object(m.jsx)(O.a, {
                    color: "warning",
                    style: {padding: "20px"},
                    isOpen: c.length > 0,
                    children: c[0]
                }), Object(m.jsx)(O.a, {
                    color: "warning",
                    style: {padding: "20px"},
                    isOpen: c.length > 1,
                    children: c[1]
                }), Object(m.jsxs)("p", {
                    className: "text-center",
                    children: ["You are logged in as ", t, ". Go to the", " ", Object(m.jsx)(d.b, {
                        className: "text-danger",
                        to: "/vehicles",
                        children: "Vehicles"
                    }), " ", "page to start a ride."]
                }), Object(m.jsx)("br", {}), r.map((function (e) {
                    return Object(m.jsxs)("div", {
                        style: {width: "100%", lineHeight: "2", fontSize: "15px"},
                        children: [Object(m.jsxs)("div", {
                            className: "text",
                            children: [Object(m.jsx)("div", {
                                className: "text-left",
                                style: {marginRight: "20px"},
                                children: Object(m.jsxs)("h5", {children: ["Vehicle ID: ", e.vehicle_id]})
                            }), Object(m.jsx)("div", {
                                children: e.end_time ? Object(m.jsxs)("p", {
                                    className: "card-title text-right",
                                    children: [Object(m.jsx)("span", {
                                        style: {color: "#FF8C00"},
                                        children: "\u25cf"
                                    }), " Inactive"]
                                }) : Object(m.jsxs)("p", {
                                    className: "card-title text-right",
                                    children: [Object(m.jsx)("span", {
                                        style: {color: "lightgreen"},
                                        children: "\u25cf"
                                    }), " Active"]
                                })
                            })]
                        }), Object(m.jsxs)("div", {
                            style: {
                                display: "flex",
                                justifyContent: "start",
                                alignItems: "center",
                                alignSelf: "center"
                            },
                            children: [Object(m.jsxs)("div", {
                                className: "text-left",
                                style: {marginRight: "30px"},
                                children: [Object(m.jsx)("div", {
                                    children: Object(m.jsx)("p", {
                                        className: "card-text",
                                        children: "Start time"
                                    })
                                }), Object(m.jsx)("div", {
                                    children: Object(m.jsx)("p", {
                                        className: "card-text",
                                        children: e.start_time
                                    })
                                })]
                            }), Object(m.jsxs)("div", {
                                style: {marginLeft: "80px"},
                                children: [Object(m.jsx)("div", {
                                    children: Object(m.jsx)("p", {
                                        className: "card-text",
                                        children: "End time"
                                    })
                                }), Object(m.jsx)("div", {
                                    children: Object(m.jsx)("p", {
                                        className: "card-text",
                                        children: e.end_time ? e.end_time : "None"
                                    })
                                })]
                            }), e.end_time ? null : Object(m.jsx)(d.b, {
                                to: "/ridedetail/".concat(e.vehicle_id, "/true"),
                                className: "btn btn-danger",
                                style: {
                                    marginLeft: "80px",
                                    backgroundColor: "#FF3565",
                                    width: "10%",
                                    height: "calc(1.5em + 1.125rem + 2px)"
                                },
                                children: "View ride"
                            })]
                        }), Object(m.jsx)("hr", {})]
                    }, e.id)
                }))]
            }) : null
        }));
        c(91);
        var S = Object(b.b)((function (e) {
            return {vehicles: e.data.vehicles, email: e.data.email, newVehicleID: e.data.newVehicleID}
        }), {
            AddNewVehicleID: function (e) {
                return {type: "NEWVEH", payload: e}
            }
        })((function (e) {
            e.AddVehicle, e.newVehicleID;
            var t = e.AddNewVehicleID, c = Object(a.useState)("Bike"), i = Object(l.a)(c, 2), s = i[0], n = i[1],
                o = Object(a.useState)(""), b = Object(l.a)(o, 2), h = b[0], u = b[1], x = Object(a.useState)(""),
                p = Object(l.a)(x, 2), O = p[0], g = p[1], f = Object(a.useState)(""), v = Object(l.a)(f, 2), y = v[0],
                N = v[1], E = Object(a.useState)(""), S = Object(l.a)(E, 2), w = S[0], C = S[1],
                I = Object(a.useState)(""), D = Object(l.a)(I, 2), A = D[0], R = D[1], L = Object(a.useState)(""),
                F = Object(l.a)(L, 2), B = F[0], V = F[1], T = Object(a.useState)(""), k = Object(l.a)(T, 2), H = k[0],
                U = k[1], W = Object(a.useState)(""), P = Object(l.a)(W, 2), z = P[0], X = P[1], Y = Object(r.g)(),
                q = Object(a.useState)(""), G = Object(l.a)(q, 2), Q = G[0], J = G[1], K = Object(a.useState)(""),
                M = Object(l.a)(K, 2), Z = M[0], _ = M[1], $ = Object(a.useState)(""), ee = Object(l.a)($, 2),
                te = ee[0], ce = ee[1], ae = Object(a.useState)(""), ie = Object(l.a)(ae, 2), se = ie[0], ne = ie[1],
                le = Object(a.useState)(""), re = Object(l.a)(le, 2), de = re[0], oe = re[1],
                je = Object(a.useState)(!1), be = Object(l.a)(je, 2), he = be[0], ue = be[1],
                xe = Object(a.useState)(!1), pe = Object(l.a)(xe, 2), Oe = pe[0], me = pe[1],
                ge = Object(a.useState)(!1), fe = Object(l.a)(ge, 2), ve = fe[0], ye = fe[1],
                Ne = Object(a.useState)(!1), Ee = Object(l.a)(Ne, 2), Se = (Ee[0], Ee[1], Object(a.useState)(!1)),
                we = Object(l.a)(Se, 2), Ce = we[0], Ie = we[1], De = Object(a.useState)(!1), Ae = Object(l.a)(De, 2),
                Re = Ae[0], Le = Ae[1], Fe = Object(a.useState)(!1), Be = Object(l.a)(Fe, 2);
            return Be[0], Be[1], Object(m.jsxs)("div", {
                className: "container",
                style: {paddingLeft: "0px", paddingRight: "0px"},
                children: [Object(m.jsx)("h1", {
                    style: {margin: "20px", textAlign: "center", fontSize: "43px"},
                    children: "Add a vehicle"
                }), Object(m.jsx)("div", {
                    style: {
                        display: "flex",
                        justifyContent: "start",
                        alignItems: "center",
                        marginTop: "20px",
                        marginBottom: "90px",
                        width: "800px"
                    }, children: Object(m.jsx)("div", {
                        style: {width: "400px"}, children: Object(m.jsxs)("form", {
                            children: [Object(m.jsxs)("div", {
                                class: "form-group",
                                children: [Object(m.jsx)("label", {
                                    for: "exampleInputEmail1",
                                    children: "Type"
                                }), Object(m.jsxs)("select", {
                                    className: "form-control",
                                    onChange: function (e) {
                                        n(e.target.value)
                                    },
                                    style: {height: "calc(1.5em + 1.125rem + 2px)"},
                                    children: [Object(m.jsx)("option", {
                                        value: "Bike",
                                        children: "Bike"
                                    }), Object(m.jsx)("option", {
                                        value: "Scooter",
                                        children: "Scooter"
                                    }), Object(m.jsx)("option", {value: "Skateboard", children: "Skateboard"})]
                                })]
                            }), Object(m.jsxs)("div", {
                                class: "form-group",
                                children: [Object(m.jsx)("label", {
                                    for: "exampleInputPassword1",
                                    children: "Longitude"
                                }), Object(m.jsx)("input", {
                                    onChange: function (e) {
                                        u(e.target.value), isNaN(e.target.value) || "" == e.target.value || e.target.value > 180 || e.target.value < -180 ? ye(!1) : (ye(!0), ce(!0))
                                    },
                                    style: {height: "calc(1.5em + 1.125rem + 2px)"},
                                    type: "text",
                                    class: "form-control"
                                }), !1 === te ? Object(m.jsx)("div", {children: Object(m.jsx)("p", {children: "Longitude must be between -180 and 180."})}) : null]
                            }), Object(m.jsxs)("div", {
                                class: "form-group",
                                children: [Object(m.jsx)("label", {
                                    for: "exampleInputPassword1",
                                    children: "Latitude"
                                }), Object(m.jsx)("input", {
                                    onChange: function (e) {
                                        g(e.target.value), isNaN(e.target.value) || "" == e.target.value || e.target.value > 90 || e.target.value < -90 ? me(!1) : (me(!0), _(!0))
                                    },
                                    style: {height: "calc(1.5em + 1.125rem + 2px)"},
                                    type: "text",
                                    class: "form-control"
                                }), !1 === Z ? Object(m.jsx)("div", {children: Object(m.jsx)("p", {children: "Latitude must be between -90 and 90."})}) : null]
                            }), Object(m.jsxs)("div", {
                                class: "form-group",
                                children: [Object(m.jsx)("label", {
                                    for: "exampleInputPassword1",
                                    children: "Battery (percent)"
                                }), Object(m.jsx)("input", {
                                    onChange: function (e) {
                                        N(e.target.value), isNaN(e.target.value) || "" == e.target.value || e.target.value > 100 || e.target.value < 0 || e.target.value.includes(".") ? ue(!1) : (ue(!0), J(!0))
                                    },
                                    style: {height: "calc(1.5em + 1.125rem + 2px)"},
                                    type: "text",
                                    class: "form-control"
                                }), !1 === Q ? Object(m.jsxs)("div", {children: [Object(m.jsx)("p", {children: "Not a valid integer value"}), Object(m.jsx)("p", {children: "Battery (percent) must be between 0 and 100"})]}) : null]
                            }), Object(m.jsxs)("div", {
                                class: "form-group",
                                children: [Object(m.jsx)("label", {
                                    for: "exampleInputPassword1",
                                    children: "Color"
                                }), Object(m.jsx)("input", {
                                    onChange: function (e) {
                                        C(e.target.value)
                                    },
                                    style: {height: "calc(1.5em + 1.125rem + 2px)"},
                                    type: "text",
                                    class: "form-control"
                                })]
                            }), Object(m.jsxs)("div", {
                                class: "form-group",
                                children: [Object(m.jsx)("label", {
                                    for: "exampleInputEmail1",
                                    children: "Manufacturer"
                                }), Object(m.jsx)("input", {
                                    onChange: function (e) {
                                        return R(e.target.value)
                                    },
                                    style: {height: "calc(1.5em + 1.125rem + 2px)"},
                                    type: "email",
                                    class: "form-control"
                                })]
                            }), Object(m.jsxs)("div", {
                                class: "form-group",
                                children: [Object(m.jsx)("label", {
                                    for: "exampleInputPassword1",
                                    children: "Serial Number"
                                }), Object(m.jsx)("input", {
                                    onChange: function (e) {
                                        V(e.target.value), isNaN(e.target.value) || "" == e.target.value || Number(e.target.value) < 0 || 0 == Number.isInteger(Number(e.target.value)) || e.target.value.includes(".") || e.target.value.includes("-") || e.target.value.includes("+") ? Ie(!1) : (Ie(!0), ne(!0))
                                    },
                                    style: {height: "calc(1.5em + 1.125rem + 2px)"},
                                    type: "text",
                                    class: "form-control"
                                }), !1 === se ? Object(m.jsx)("div", {children: Object(m.jsx)("p", {children: "Please input a positive integer for serial number."})}) : null]
                            }), Object(m.jsxs)("div", {
                                class: "form-group",
                                children: [Object(m.jsx)("label", {
                                    for: "exampleInputPassword1",
                                    children: "Vehicle Wear"
                                }), Object(m.jsx)("input", {
                                    onChange: function (e) {
                                        return U(e.target.value)
                                    },
                                    style: {height: "calc(1.5em + 1.125rem + 2px)"},
                                    type: "text",
                                    class: "form-control"
                                })]
                            }), Object(m.jsxs)("div", {
                                class: "form-group",
                                children: [Object(m.jsx)("label", {
                                    for: "exampleInputEmail1",
                                    children: "Purchase Date"
                                }), Object(m.jsx)("input", {
                                    onChange: function (e) {
                                        X(e.target.value), "" == e.target.value ? Le(!1) : (Le(!0), oe(!0))
                                    },
                                    style: {height: "calc(1.5em + 1.125rem + 2px)"},
                                    type: "text",
                                    class: "form-control"
                                }), !1 === de ? Object(m.jsx)("div", {children: Object(m.jsx)("p", {children: "Please input a date."})}) : null]
                            }), Object(m.jsx)(d.b, {
                                onMouseDown: function (e) {
                                    e.preventDefault(), ve || ce(!1), Oe || _(!1), he || J(!1), Ce || ne(!1), Re || oe(!1), ve && Oe && he && Ce && Re && j.a.post("/ui/vehicles", {
                                        vehicle_type: String(s),
                                        color: w,
                                        manufacturer: A,
                                        serial_number: String(B),
                                        wear: H,
                                        purchase_date: z,
                                        latitude: parseFloat(O),
                                        longitude: parseFloat(h),
                                        battery: Number(y)
                                    }).then((function (e) {
                                        setTimeout((function () {
                                            t(e.data), Y.push("/vehicledetail/".concat(e.data.id))
                                        }), 1e3)
                                    })).catch((function (e) {
                                    }))
                                },
                                class: "btn btn-danger form-control",
                                style: {backgroundColor: "#FF3565", height: "calc(1.5em + 1.125rem + 2px)"},
                                children: "Add vehicle"
                            })]
                        })
                    })
                })]
            })
        }));
        var w = Object(b.b)((function (e) {
            return {email: e.data.email}
        }), {
            deleteUserConfirm: function (e) {
                return {type: "DELETEDUSER", payload: e}
            }, logout: u
        })((function (e) {
            var t = e.email, c = e.deleteUserConfirm, i = (e.logout, Object(a.useState)("")), s = Object(l.a)(i, 2),
                n = s[0], o = s[1], b = Object(r.g)();
            return Object(a.useEffect)((function () {
                j.a.get("/ui/users/" + t).then((function (e) {
                    setTimeout((function () {
                        o(e.data)
                    }), 1e3)
                })).catch((function (e) {
                }))
            }), []), n ? Object(m.jsxs)("div", {
                className: "container",
                style: {paddingLeft: "0px", paddingRight: "0px"},
                children: [Object(m.jsxs)("h1", {
                    style: {margin: "20px", fontWeight: "bold", textAlign: "center"},
                    children: [n.user.first_name, " ", n.user.last_name]
                }), Object(m.jsxs)("p", {
                    style: {marginLeft: "40px"},
                    children: ["You are logged in as ", n.user.email]
                }), Object(m.jsx)("div", {
                    style: {
                        display: "flex",
                        justifyContent: "center",
                        alignItems: "start",
                        flexDirection: "column",
                        textAlign: "left",
                        border: "1px solid",
                        borderColor: "#E9E9E9"
                    },
                    children: Object(m.jsxs)("table", {
                        className: "table text-left",
                        style: {border: "1px solid black", marginLeft: "50px", marginTop: "15px", width: "400px"},
                        children: [Object(m.jsx)("tr", {
                            children: Object(m.jsx)("td", {
                                style: {
                                    textAlign: "center",
                                    height: "60px",
                                    padding: "30px",
                                    borderTopColor: "black",
                                    fontWeight: "700",
                                    color: "#737491"
                                }, children: "Phone Number(s)"
                            })
                        }), 0 != n.user.phone_numbers.length ? n.user.phone_numbers.map((function (e) {
                            return Object(m.jsx)("tr", {
                                children: Object(m.jsx)("td", {
                                    style: {
                                        textAlign: "center",
                                        paddingTop: "0px",
                                        paddingBottom: "0px",
                                        borderTopColor: "black",
                                        color: "#737491"
                                    }, children: e
                                })
                            })
                        })) : null]
                    })
                }), Object(m.jsx)("div", {
                    style: {
                        padding: "20px",
                        backgroundColor: "#FFF9F2",
                        width: "18rem",
                        marginTop: "30px"
                    },
                    children: Object(m.jsx)(d.b, {
                        to: "/",
                        className: "btn btn-danger",
                        style: {backgroundColor: "#FF3565", width: "100%", height: "calc(1.5em + 1.125rem + 2px)"},
                        onClick: function () {
                            j.a.delete("/ui/users/" + t).then((function (e) {
                                c(e.data), setTimeout((function () {
                                    sessionStorage.clear(), c(e.data)
                                }), 1500)
                            })).catch((function (e) {
                                sessionStorage.clear(), b.push("/")
                            })).then((function () {
                                sessionStorage.clear(), b.push("/")
                            }))
                        },
                        children: "Delete my account"
                    })
                })]
            }) : null
        })), C = (c(92), c(40)), I = c.n(C), D = Object(b.b)((function (e) {
            return {
                vehicles: e.data.vehicles,
                email: e.data.email,
                RideEndDetails: e.data.RideEndDetails,
                newVehicleID: e.data.newVehicleID.id
            }
        }), {
            removedVehicle: function (e) {
                return {type: "REMOVEDVEHICLE", payload: e}
            }, resetDash: x
        })((function (e) {
            var t = e.match, c = e.email, i = e.removedVehicle, s = e.newVehicleID, n = (e.RideEndDetails, e.resetDash),
                r = Object(a.useState)(""), o = Object(l.a)(r, 2), b = o[0], h = o[1], u = Object(a.useState)(""),
                x = Object(l.a)(u, 2), p = x[0], g = x[1], f = Object(a.useState)(""), v = Object(l.a)(f, 2), y = v[0],
                N = v[1];
            Object(a.useEffect)((function () {
                g(t.params.id), N(void 0 != s), j.a.get("/ui/vehicles/" + t.params.id).then((function (e) {
                    h(e.data)
                })).catch((function (e) {
                    console.log("Exception caught and ignored. \ntag: 04c7e810-1382-4943-8b92-d4b0a9643409\n" + "error: ".concat(e, "\n\n") + "Message: ".concat(e.message))
                })).then((function (e) {
                    setTimeout((function () {
                        N(!1), n()
                    }), 1e4)
                })), setTimeout((function () {
                    n()
                }), 1e4)
            }), []);
            return b ? Object(m.jsxs)("div", {
                style: {margin: "20px", textAlign: "center"},
                children: [Object(m.jsxs)("h1", {children: ["Vehicle ", b.id]}), Object(m.jsx)("div", {
                    className: "container",
                    children: Object(m.jsxs)(O.a, {
                        color: "warning",
                        style: {padding: "20px"},
                        isOpen: y,
                        children: ["Vehicle added id: ", s]
                    })
                }), Object(m.jsxs)("div", {
                    className: "col-lg-4 col-md-6 col-lg-6 container",
                    style: {
                        display: "flex",
                        flexDirection: "column",
                        alignItems: "start",
                        marginBottom: "10px",
                        width: "90%",
                        marginRight: "30%"
                    },
                    children: [Object(m.jsxs)("div", {
                        className: "card",
                        style: {margin: "20px", marginLeft: "-50px"},
                        children: [Object(m.jsx)("div", {
                            className: "topboders",
                            style: {height: "170px"}
                        }), Object(m.jsxs)("div", {
                            className: "card-body",
                            children: [Object(m.jsxs)("div", {
                                style: {display: "flex", justifyContent: "space-between"},
                                children: [Object(m.jsx)("div", {
                                    children: Object(m.jsx)("h4", {
                                        className: "card-title text-left text-capitalize",
                                        children: String(b.vehicle_info.type)
                                    })
                                }), Object(m.jsx)("div", {
                                    children: b.in_use ? Object(m.jsxs)("p", {
                                        className: "card-title text-right",
                                        children: [Object(m.jsx)("span", {
                                            style: {color: "#FF8C00"},
                                            children: "\u25cf"
                                        }), " Unavailable"]
                                    }) : Object(m.jsxs)("p", {
                                        className: "card-title text-right",
                                        children: [Object(m.jsx)("span", {
                                            style: {color: "lightgreen"},
                                            children: "\u25cf"
                                        }), " Available"]
                                    })
                                })]
                            }), Object(m.jsx)("p", {
                                className: "text-left",
                                children: "ID"
                            }), Object(m.jsx)("p", {
                                className: "card-text text-left",
                                children: b.id
                            }), Object(m.jsx)("hr", {}), Object(m.jsxs)("div", {
                                style: {
                                    display: "flex",
                                    justifyContent: "start"
                                },
                                children: [Object(m.jsxs)("div", {
                                    className: "text-left",
                                    style: {marginRight: "60px"},
                                    children: [Object(m.jsx)("div", {
                                        children: Object(m.jsx)("p", {
                                            className: "card-text",
                                            children: "Color"
                                        })
                                    }), Object(m.jsx)("div", {
                                        children: Object(m.jsx)("p", {
                                            className: "card-text text-capitalize",
                                            children: b.vehicle_info.color
                                        })
                                    })]
                                }), Object(m.jsxs)("div", {
                                    className: "text-left",
                                    style: {marginLeft: "80px"},
                                    children: [Object(m.jsx)("div", {
                                        children: Object(m.jsx)("p", {
                                            className: "card-text text-left",
                                            children: "Brand"
                                        })
                                    }), Object(m.jsx)("div", {
                                        children: Object(m.jsx)("p", {
                                            className: "card-text",
                                            children: b.vehicle_info.purchase_information.manufacturer
                                        })
                                    })]
                                }), Object(m.jsx)("input", {type: "hidden", value: "f"})]
                            }), Object(m.jsx)("hr", {}), Object(m.jsxs)("div", {
                                style: {
                                    display: "flex",
                                    justifyContent: "start"
                                },
                                children: [Object(m.jsxs)("div", {
                                    className: "text-left",
                                    style: {marginRight: "60px"},
                                    children: [Object(m.jsx)("div", {
                                        children: Object(m.jsx)("p", {
                                            className: "card-text",
                                            children: "Battery"
                                        })
                                    }), Object(m.jsx)("div", {
                                        children: Object(m.jsxs)("p", {
                                            className: "card-text",
                                            children: [b.battery, "%"]
                                        })
                                    })]
                                }), Object(m.jsxs)("div", {
                                    className: "text-left",
                                    style: {marginLeft: "66px"},
                                    children: [Object(m.jsx)("div", {
                                        children: Object(m.jsx)("p", {
                                            className: "card-text text-left",
                                            children: "Purchase Date"
                                        })
                                    }), Object(m.jsx)("div", {
                                        children: Object(m.jsx)("p", {
                                            className: "card-text",
                                            children: b.vehicle_info.purchase_information.purchase_date
                                        })
                                    })]
                                }), Object(m.jsx)("input", {type: "hidden", value: "ff"})]
                            }), Object(m.jsx)("hr", {}), Object(m.jsxs)("div", {
                                style: {
                                    display: "flex",
                                    justifyContent: "start"
                                },
                                children: [Object(m.jsxs)("div", {
                                    className: "text-left",
                                    style: {marginRight: "60px"},
                                    children: [Object(m.jsx)("div", {
                                        children: Object(m.jsx)("p", {
                                            className: "card-text",
                                            children: "Serial #"
                                        })
                                    }), Object(m.jsx)("div", {
                                        children: Object(m.jsx)("p", {
                                            className: "card-text",
                                            children: b.serial_number
                                        })
                                    })]
                                }), Object(m.jsxs)("div", {
                                    className: "text-left",
                                    style: {marginLeft: "65px"},
                                    children: [Object(m.jsx)("div", {
                                        children: Object(m.jsx)("p", {
                                            className: "card-text text-left",
                                            children: "Wear"
                                        })
                                    }), Object(m.jsx)("div", {
                                        children: Object(m.jsx)("p", {
                                            className: "card-text text-capitalize",
                                            children: b.vehicle_info.wear
                                        })
                                    })]
                                }), Object(m.jsx)("input", {type: "hidden", value: "f"})]
                            }), " ", Object(m.jsx)("hr", {}), b.in_use ? null : Object(m.jsx)(d.b, {
                                onClick: function () {
                                    try {
                                        j.a.post("/ui/rides/start", {email: c, vehicle_id: p})
                                    } catch (e) {
                                        console.log("Exception caught and ignored. \ntag: 1c9181a1-f39e-470c-99a2-f39dd8869fd1\nerror: {err}\n\nMessage: {err.message}")
                                    }
                                },
                                to: "/ridedetail/".concat(t.params.id, "/false"),
                                className: "btn btn-danger",
                                style: {
                                    width: "100%",
                                    backgroundColor: "#FF3565",
                                    height: "calc(1.5em + 1.125rem + 2px)",
                                    marginBottom: "5px"
                                },
                                children: "Start ride"
                            }), Object(m.jsx)(d.b, {
                                to: "/vehicles",
                                className: "btn btn-danger",
                                style: {
                                    width: "80%",
                                    backgroundColor: "#FF3565",
                                    marginTop: "15px",
                                    marginRight: "100%",
                                    height: "calc(1.5em + 1.125rem + 2px)"
                                },
                                onClick: function () {
                                    j.a.delete("/ui/vehicles/" + t.params.id).then((function (e) {
                                    })).then((function () {
                                        i(t.params.id)
                                    })).catch((function (e) {
                                        i("NOT DELETED: " + t.params.id), console.log("Generic error caught and assumed to be a vehicle thatwas not deleted because it is in use.\nerror: {error}\n\nmessage: {error.message}")
                                    }))
                                },
                                children: "Remove vehicle"
                            })]
                        })]
                    }), Object(m.jsx)("h3", {
                        style: {marginBottom: "25px", marginTop: "10px", marginLeft: "-50px"},
                        children: "Location History"
                    }), b.locationHistory.map((function (e) {
                        return Object(m.jsxs)("div", {
                            style: {lineHeight: "1.5", marginLeft: "-50px"},
                            children: [Object(m.jsxs)("h4", {children: ["Timestamp: ", I()(e.ts).format("YYYY-MM-DD HH:mm:ss")]}), Object(m.jsxs)("div", {
                                style: {
                                    display: "flex",
                                    justifyContent: "space-between"
                                },
                                children: [Object(m.jsxs)("div", {
                                    className: "text-left",
                                    children: [Object(m.jsx)("div", {
                                        children: Object(m.jsx)("p", {
                                            className: "card-text",
                                            children: "Longitude"
                                        })
                                    }), Object(m.jsx)("div", {
                                        children: Object(m.jsx)("p", {
                                            className: "card-text",
                                            children: e.longitude
                                        })
                                    })]
                                }), Object(m.jsxs)("div", {
                                    className: "text-left",
                                    children: [Object(m.jsx)("div", {
                                        children: Object(m.jsx)("p", {
                                            className: "card-text",
                                            children: "Latitude"
                                        })
                                    }), Object(m.jsx)("div", {
                                        children: Object(m.jsx)("p", {
                                            className: "card-text",
                                            children: e.latitude
                                        })
                                    })]
                                }), Object(m.jsx)("input", {type: "hidden", value: "f"})]
                            }), Object(m.jsx)("hr", {})]
                        })
                    }))]
                }, b.id)]
            }) : null
        })), A = c(44), R = Object(b.b)((function (e) {
            return {vehicles: e.data.vehicles, email: e.data.email}
        }), {
            rideEndedDetails: function (e) {
                return {type: "RIDEENDED", payload: e}
            }
        })((function (e) {
            var t = e.match, c = e.email, i = e.rideEndedDetails, s = Object(a.useState)(""), n = Object(l.a)(s, 2),
                r = n[0], o = n[1], b = Object(a.useState)(""), h = Object(l.a)(b, 2), u = h[0], x = h[1],
                p = Object(a.useState)(""), O = Object(l.a)(p, 2), g = O[0], f = O[1], v = Object(a.useState)(""),
                y = Object(l.a)(v, 2), N = y[0], E = y[1], S = Object(a.useState)(""), w = Object(l.a)(S, 2), C = w[0],
                I = w[1], D = Object(a.useState)(""), R = Object(l.a)(D, 2), L = R[0], F = R[1],
                B = Object(a.useState)(""), V = Object(l.a)(B, 2), T = V[0], k = V[1], H = Object(a.useState)(""),
                U = Object(l.a)(H, 2), W = U[0], P = U[1], z = Object(a.useState)(!1), X = Object(l.a)(z, 2), Y = X[0],
                q = X[1], G = Object(a.useState)(!1), Q = Object(l.a)(G, 2), J = Q[0], K = Q[1],
                M = Object(a.useState)(!1), Z = Object(l.a)(M, 2), _ = Z[0], $ = Z[1], ee = Object(a.useState)(!1),
                te = Object(l.a)(ee, 2), ce = te[0], ae = te[1];
            Object(a.useEffect)((function () {
                setTimeout((function () {
                    try {
                        j.a.get("/ui/vehicles/location/" + t.params.id).then((function (e) {
                            E(e.data), I(e.data.id)
                        }))
                    } catch (e) {
                        console.log("Exception caught and ignored. \ntag: 5d241ac7-cddf-434f-bbc5-34cdc67e6c6a\n" + "error: ".concat(e, "\n\n") + "Message: ".concat(e.message))
                    }
                }), 1e3)
            }), [J, _, Y, L, W, T]);
            return N ? Object(m.jsxs)("div", {
                children: [Object(m.jsxs)("h1", {
                    className: "text-center",
                    style: {marginTop: "30px", fontWeight: "600"},
                    children: ["Riding a ", N.vehicle_info.type]
                }), Object(m.jsxs)("div", {
                    className: "container col-md-6",
                    style: {marginRight: "35.8%"},
                    children: ["true" != t.params.view ? Object(m.jsxs)(A.a, {
                        variant: "warning",
                        children: ["Ride started with vehicle ", t.params.id]
                    }, 1) : null, Object(m.jsxs)("h5", {
                        style: {fontWeight: "500", fontSize: "22px", color: "black"},
                        children: ["Vehicle ID: ", N.id]
                    }), Object(m.jsxs)("div", {
                        style: {display: "flex", justifyContent: "space-between", width: "75%"},
                        children: [Object(m.jsxs)("div", {
                            className: "text-left",
                            children: [Object(m.jsx)("div", {
                                children: Object(m.jsx)("p", {
                                    className: "card-text",
                                    children: "Start Time"
                                })
                            }), Object(m.jsx)("div", {
                                children: Object(m.jsx)("p", {
                                    className: "card-text",
                                    children: N.last_checkin
                                })
                            })]
                        }), Object(m.jsxs)("div", {
                            className: "text-left",
                            children: [Object(m.jsx)("div", {
                                children: Object(m.jsx)("p", {
                                    className: "card-text",
                                    children: "Longitude"
                                })
                            }), Object(m.jsx)("div", {
                                children: Object(m.jsx)("p", {
                                    className: "card-text",
                                    children: N.last_longitude
                                })
                            })]
                        }), Object(m.jsxs)("div", {
                            className: "text-left",
                            children: [Object(m.jsx)("div", {
                                children: Object(m.jsx)("p", {
                                    className: "card-text",
                                    children: "Latitude"
                                })
                            }), Object(m.jsx)("div", {
                                children: Object(m.jsx)("p", {
                                    className: "card-text",
                                    children: N.last_latitude
                                })
                            })]
                        }), Object(m.jsxs)("div", {
                            className: "text-left",
                            children: [Object(m.jsx)("div", {
                                children: Object(m.jsx)("p", {
                                    className: "card-text",
                                    children: "Battery"
                                })
                            }), Object(m.jsx)("div", {
                                children: Object(m.jsxs)("p", {
                                    className: "card-text",
                                    children: [N.battery, "%"]
                                })
                            })]
                        }), Object(m.jsx)("input", {type: "hidden", value: N.id})]
                    }), Object(m.jsx)("div", {
                        style: {
                            display: "flex",
                            justifyContent: "start",
                            alignItems: "center",
                            marginTop: "30px",
                            marginBottom: "90px",
                            lineHeight: "1.5"
                        }, children: Object(m.jsx)("div", {
                            children: Object(m.jsxs)("form", {
                                style: {
                                    width: "600px",
                                    justifyContent: "stretch",
                                    border: "1px solid #E9E9E9",
                                    padding: "15px"
                                },
                                children: [Object(m.jsx)("p", {children: "When you're finished riding, please input the following and end your ride:"}), Object(m.jsxs)("div", {
                                    class: "form-group",
                                    children: [Object(m.jsx)("label", {
                                        for: "exampleInputEmail1",
                                        children: "Longitude"
                                    }), Object(m.jsx)("input", {
                                        style: {height: "calc(1.5em + 1.125rem + 2px)"},
                                        type: "email",
                                        onChange: function (e) {
                                            o(e.target.value), isNaN(e.target.value) || "" == e.target.value || e.target.value > 180 || e.target.value < -180 ? $(!1) : ($(!0), P(!0))
                                        },
                                        class: "form-control",
                                        id: "exampleInputEmail1",
                                        "aria-describedby": "emailHelp"
                                    }), !1 === W ? Object(m.jsx)("div", {children: Object(m.jsx)("p", {children: "Longitude must be between -180 and 180."})}) : null]
                                }), Object(m.jsxs)("div", {
                                    class: "form-group",
                                    children: [Object(m.jsx)("label", {
                                        for: "exampleInputEmail1",
                                        children: "Latitude"
                                    }), Object(m.jsx)("input", {
                                        style: {height: "calc(1.5em + 1.125rem + 2px)"},
                                        type: "text",
                                        onChange: function (e) {
                                            x(e.target.value), isNaN(e.target.value) || "" == e.target.value || e.target.value > 90 || e.target.value < -90 ? K(!1) : (K(!0), k(!0))
                                        },
                                        class: "form-control",
                                        id: "firstname",
                                        "aria-describedby": "password"
                                    }), !1 === T ? Object(m.jsx)("div", {children: Object(m.jsx)("p", {children: "Latitude must be between -90 and 90."})}) : null]
                                }), Object(m.jsxs)("div", {
                                    class: "form-group",
                                    children: [Object(m.jsx)("label", {
                                        for: "exampleInputEmail1",
                                        children: "Battery (percent)"
                                    }), Object(m.jsx)("input", {
                                        style: {height: "calc(1.5em + 1.125rem + 2px)"},
                                        type: "text",
                                        required: !0,
                                        onChange: function (e) {
                                            f(e.target.value), isNaN(e.target.value) || "" == e.target.value || e.target.value > 100 || e.target.value < 0 || e.target.value.includes(".") ? q(!1) : (q(!0), F(!0))
                                        },
                                        class: "form-control",
                                        id: "lastname",
                                        "aria-describedby": "password"
                                    }), !1 === L ? Object(m.jsxs)("div", {children: [Object(m.jsx)("p", {children: "Not a valid integer value"}), Object(m.jsx)("p", {children: "Battery (percent) must be between 0 and 100"})]}) : null]
                                }), Object(m.jsx)(d.b, {
                                    to: ce ? "/rides" : "#",
                                    onMouseDown: function () {
                                        if (_ || P(!1), J || k(!1), Y || F(!1), _ && J && Y) try {
                                            ae(!0), j.a.post("/ui/rides/end", {
                                                email: c,
                                                vehicle_id: C,
                                                longitude: r,
                                                latitude: u,
                                                battery: Number(g)
                                            }).then((function (e) {
                                                i(e.data.messages)
                                            })).catch((function (e) {
                                                console.log("Exception caught and ignored while ending a ride.\ntag: 4e77642f-a0ae-4c0e-b692-78f2b88d2417\n" + "error: ".concat(e, "\n\n") + "Message: ".concat(e.message))
                                            }))
                                        } catch (e) {
                                            console.log("Exception caught and ignored while sending a /rides/end POST request. \ntag: b11e32c7-2f54-406b-b00c-6aa817bd4319\n" + "error: ".concat(e, "\n\n") + "Message: ".concat(e.message))
                                        }
                                    },
                                    type: "button",
                                    class: "btn btn-danger form-control",
                                    style: {height: "calc(1.5em + 1.125rem + 2px)", backgroundColor: "#FF3565"},
                                    children: "End Ride"
                                })]
                            })
                        })
                    })]
                })]
            }) : null
        })), L = (c(94), Object(b.b)((function (e) {
            return {email: e.data.email, registeredUser: e.data.registeredUser}
        }), {
            newRegistration: function (e) {
                return {type: "NEWEMAIL", payload: e}
            }
        })((function (e) {
            var t = e.newRegistration, c = Object(a.useState)(""), i = Object(l.a)(c, 2), s = i[0], n = i[1],
                d = Object(a.useState)(""), o = Object(l.a)(d, 2), b = o[0], h = o[1], u = Object(a.useState)([]),
                x = Object(l.a)(u, 2), g = x[0], f = x[1], v = Object(a.useState)(""), y = Object(l.a)(v, 2), N = y[0],
                E = y[1], S = Object(a.useState)(""), w = Object(l.a)(S, 2), C = w[0], I = w[1],
                D = Object(a.useState)(""), A = Object(l.a)(D, 2), R = A[0], L = A[1], F = Object(a.useState)(!1),
                B = Object(l.a)(F, 2), V = (B[0], B[1]), T = Object(a.useState)(!1), k = Object(l.a)(T, 2), H = k[0],
                U = k[1], W = Object(a.useState)(!1), P = Object(l.a)(W, 2), z = P[0], X = P[1],
                Y = Object(a.useState)(!1), q = Object(l.a)(Y, 2), G = q[0], Q = q[1], J = Object(a.useState)(""),
                K = Object(l.a)(J, 2), M = K[0], Z = K[1], _ = Object(a.useState)(""), $ = Object(l.a)(_, 2), ee = $[0],
                te = $[1], ce = Object(a.useState)(""), ae = Object(l.a)(ce, 2), ie = ae[0], se = ae[1],
                ne = Object(r.g)();
            return Object(m.jsx)("div", {
                className: "App", children: Object(m.jsx)("div", {
                    className: "container",
                    style: {marginLeft: "270px", height: "100vh"},
                    children: Object(m.jsxs)("div", {
                        children: [Object(m.jsxs)("div", {
                            style: {lineHeight: "1.2", paddingTop: "100px"},
                            children: [Object(m.jsx)("img", {
                                src: p,
                                style: {marginBottom: "50px"}
                            }), Object(m.jsxs)("div", {
                                style: {marginTop: "20px", lineHeight: "2"},
                                children: [Object(m.jsx)("h1", {
                                    style: {fontWeight: "600", fontSize: "40px"},
                                    children: "Sign Up"
                                }), Object(m.jsx)("div", {
                                    className: "col-6-lg", style: {width: "600px"}, children: Object(m.jsxs)("form", {
                                        children: [N ? Object(m.jsxs)(O.a, {
                                            color: "warning",
                                            style: {padding: "20px"},
                                            children: ["Registration successful! You can now log in as ", R]
                                        }) : null, !1 === N ? Object(m.jsx)(O.a, {
                                            color: "warning",
                                            style: {padding: "20px"},
                                            children: "Registration failed. Make sure that you choose a unique email!"
                                        }) : null, !1 === C ? Object(m.jsx)(O.a, {
                                            color: "warning",
                                            style: {padding: "20px"},
                                            children: "Registration failed. Please fill out required fields!"
                                        }) : null, Object(m.jsxs)("div", {
                                            class: "form-group",
                                            children: [Object(m.jsx)("label", {
                                                for: "exampleInputEmail1",
                                                children: "Email"
                                            }), Object(m.jsx)("input", {
                                                type: "email",
                                                onChange: function (e) {
                                                    L(e.target.value), "" == e.target.value ? Q(!1) : (Q(!0), se(!0))
                                                },
                                                class: "form-control",
                                                id: "exampleInputEmail1",
                                                "aria-describedby": "emailHelp"
                                            }), !1 === ie ? Object(m.jsx)("div", {children: Object(m.jsx)("p", {children: "Please enter an email address."})}) : null]
                                        }), Object(m.jsxs)("div", {
                                            class: "form-group",
                                            children: [Object(m.jsx)("label", {
                                                for: "exampleInputEmail1",
                                                children: "First name:"
                                            }), Object(m.jsx)("input", {
                                                type: "text",
                                                onChange: function (e) {
                                                    h(e.target.value), "" == e.target.value ? U(!1) : (U(!0), Z(!0))
                                                },
                                                class: "form-control",
                                                id: "firstname",
                                                "aria-describedby": "password"
                                            }), !1 === M ? Object(m.jsx)("div", {children: Object(m.jsx)("p", {children: "Please enter your first name."})}) : null]
                                        }), Object(m.jsxs)("div", {
                                            class: "form-group",
                                            children: [Object(m.jsx)("label", {
                                                for: "exampleInputEmail1",
                                                children: "Last name:"
                                            }), Object(m.jsx)("input", {
                                                type: "text", onChange: function (e) {
                                                    n(e.target.value), "" == e.target.value ? X(!1) : (X(!0), te(!0))
                                                }, class: "form-control", id: "lastname", "aria-describedby": "password"
                                            }), !1 === ee ? Object(m.jsx)("div", {children: Object(m.jsx)("p", {children: "Please enter your last name."})}) : null]
                                        }), Object(m.jsxs)("div", {
                                            class: "form-group",
                                            children: [Object(m.jsx)("label", {
                                                for: "exampleInputEmail1",
                                                children: "Phone number"
                                            }), Object(m.jsx)("input", {
                                                type: "text", onChange: function (e) {
                                                    return f(e.target.value)
                                                }, class: "form-control", id: "password", "aria-describedby": "password"
                                            })]
                                        }), Object(m.jsx)("button", {
                                            onClick: function () {
                                                if (G || se(!1), H || Z(!1), z || te(!1), G && H && z) {
                                                    t(String(R));
                                                    try {
                                                        j.a.post("/ui/users", {
                                                            email: String(R),
                                                            last_name: String(s),
                                                            first_name: String(b),
                                                            phone_numbers: 0 != g.length ? g.split(",") : []
                                                        }).then((function () {
                                                            E(!0), V(!1), I(!0), ne.push("/")
                                                        })).catch((function (e) {
                                                            I(!0), E(!1), V(!1)
                                                        }))
                                                    } catch (e) {
                                                        I(!0), E(!1), V(!1)
                                                    }
                                                }
                                            },
                                            type: "button",
                                            class: "btn btn-danger form-control",
                                            style: {backgroundColor: "#FF3565"},
                                            children: "Register"
                                        }), Object(m.jsxs)("p", {
                                            className: "text-center",
                                            style: {marginTop: "5px"},
                                            children: ["Already have an account? ", Object(m.jsx)("a", {
                                                href: "/",
                                                "data-dismiss": "modal",
                                                style: {color: "#FF3565"},
                                                children: "Log in"
                                            })]
                                        }), Object(m.jsx)("p", {
                                            style: {color: "#737491"},
                                            children: "* For multiple numbers, please separate with a comma."
                                        })]
                                    })
                                })]
                            })]
                        }), Object(m.jsx)("p", {
                            style: {
                                position: "absolute",
                                bottom: "0",
                                marginLeft: "10px",
                                fontSize: "14px",
                                marginBottom: "40px",
                                color: "#737491"
                            }, children: "\xa9 CockroachLabs"
                        })]
                    })
                })
            })
        })));
        var F = Object(b.b)((function (e) {
            return {login: e.data.login, email: e.data.email, location: e.data.location}
        }), {logins: h})((function (e) {
            var t = e.login, c = e.logins, i = (e.email, e.location), s = Object(a.useState)(""), n = Object(l.a)(s, 2),
                o = n[0], j = n[1], b = Object(a.useState)(""), h = Object(l.a)(b, 2), u = h[0], x = h[1];
            return Object(a.useEffect)((function () {
                var e = sessionStorage.getItem("user");
                x(e), null != e && c(e), j("/" != i && "/register" != i), console.log("Log in status :" + t)
            }), [o, i, u]), Object(m.jsxs)(d.a, {
                children: [Object(m.jsx)(y, {}), Object(m.jsxs)(r.d, {
                    children: [Object(m.jsx)(r.b, {
                        exact: !0, path: "/", component: 1 != t ? function () {
                            return Object(m.jsxs)("div", {
                                children: [Object(m.jsx)(r.b, {
                                    exact: !0,
                                    path: "/",
                                    render: function () {
                                        return Object(m.jsx)(r.a, {to: "/"})
                                    }
                                }), Object(m.jsx)(r.b, {path: "/", component: g})]
                            })
                        } : function () {
                            return Object(m.jsxs)("div", {
                                children: [Object(m.jsx)(r.b, {
                                    path: "/vehicles",
                                    render: function () {
                                        return Object(m.jsx)(r.a, {to: "/vehicles"})
                                    }
                                }), Object(m.jsx)(r.b, {path: "/", component: N})]
                            })
                        }
                    }), Object(m.jsx)(r.b, {
                        path: "/register", component: function () {
                            return Object(m.jsxs)("div", {
                                children: [Object(m.jsx)(r.b, {
                                    exact: !0,
                                    path: "/register",
                                    render: function () {
                                        return Object(m.jsx)(r.a, {to: "/register"})
                                    }
                                }), Object(m.jsx)(r.b, {path: "/register", component: L})]
                            })
                        }
                    }), Object(m.jsx)(r.b, {path: "/vehicles", component: N}), Object(m.jsx)(r.b, {
                        path: "/rides",
                        component: E
                    }), Object(m.jsx)(r.b, {
                        path: "/addvehicles",
                        component: S
                    }), Object(m.jsx)(r.b, {
                        path: "/vehicledetail/:id",
                        component: D
                    }), Object(m.jsx)(r.b, {
                        path: "/vehicledetail",
                        component: D
                    }), Object(m.jsx)(r.b, {
                        path: "/ridedetail/:id/:view",
                        component: R
                    }), Object(m.jsx)(r.b, {path: "/profile", component: w})]
                })]
            })
        }));
        Boolean("localhost" === window.location.hostname || "[::1]" === window.location.hostname || window.location.hostname.match(/^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/));
        var B = c(19), V = c(43), T = c(6), k = {
            vehicles: ["Char", "asd", "afdaf", "Char", "asd", "afdaf"],
            login: "",
            logout: "",
            email: "",
            newemail: "",
            deletedUser: "",
            registeredUser: "",
            ridestarted: "",
            rideended: "",
            vehicleID: null,
            newVehicleID: "",
            RideEndDetails: [],
            location: ""
        }, H = function () {
            var e = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : k,
                t = arguments.length > 1 ? arguments[1] : void 0;
            switch (t.type) {
                case"DELETEDUSER":
                    return sessionStorage.clear(), Object(T.a)(Object(T.a)({}, e), {}, {
                        logout: "",
                        login: "",
                        deletedUser: t.payload.messages[0],
                        email: null,
                        newemail: ""
                    });
                case"LOCATION":
                    return Object(T.a)(Object(T.a)({}, e), {}, {location: t.payload});
                case"VEHICLES":
                    return Object(T.a)(Object(T.a)({}, e), {}, {vehicles: t.payload});
                case"NEWEMAIL":
                    return Object(T.a)(Object(T.a)({}, e), {}, {newemail: t.payload});
                case"LOGIN":
                    return Object(T.a)(Object(T.a)({}, e), {}, {login: !0, email: t.payload});
                case"INCORRECT":
                    return Object(T.a)(Object(T.a)({}, e), {}, {login: !1});
                case"LOGOUT":
                    return sessionStorage.clear(), Object(T.a)(Object(T.a)({}, e), {}, {
                        login: !1,
                        logout: !1,
                        email: null,
                        vehicles: ["Char", "asd", "afdaf", "Char", "asd", "afdaf"],
                        newemail: "",
                        deletedUser: "",
                        registeredUser: "",
                        ridestarted: "",
                        rideended: "",
                        vehicleID: null,
                        newVehicleID: "",
                        RideEndDetails: []
                    });
                case"RESET":
                    return Object(T.a)(Object(T.a)({}, e), {}, {login: "", logout: "", email: null});
                case"REMOVEDVEHICLE":
                    return Object(T.a)(Object(T.a)({}, e), {}, {vehicleID: t.payload});
                case"RESETDASH":
                    return Object(T.a)(Object(T.a)({}, e), {}, {vehicleID: null, newVehicleID: "", RideEndDetails: []});
                case"NEWVEH":
                    return Object(T.a)(Object(T.a)({}, e), {}, {newVehicleID: t.payload});
                case"RIDEENDED":
                    return Object(T.a)(Object(T.a)({}, e), {}, {RideEndDetails: t.payload});
                case"STARTRIDE":
                    return Object(T.a)(Object(T.a)({}, e), {}, {ridestarted: !0});
                case"ENDRIDE":
                    return Object(T.a)(Object(T.a)({}, e), {}, {rideended: !0});
                default:
                    return e
            }
        }, U = Object(B.b)({data: H}), W = [V.a], P = Object(B.c)(U, {}, B.a.apply(void 0, W));
        n.a.render(Object(m.jsx)(i.a.StrictMode, {
            children: Object(m.jsx)(b.a, {
                store: P,
                children: Object(m.jsx)(F, {})
            })
        }), document.getElementById("root")), "serviceWorker" in navigator && navigator.serviceWorker.ready.then((function (e) {
            e.unregister()
        })).catch((function (e) {
            console.error(e.message)
        }))
    }
}, [[95, 1, 2]]]);
//# sourceMappingURL=main.88999e09.chunk.js.map